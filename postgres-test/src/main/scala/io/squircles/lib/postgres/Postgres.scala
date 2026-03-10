package io.squircles.lib.postgres

import com.dimafeng.testcontainers.PostgreSQLContainer
import doobie.Fragment
import doobie.util.fragment
import io.github.iltotore.iron.RefinedType
import io.github.iltotore.iron.constraint.string.Match
import io.squircles.lib.postgres.JdbcConnection.Transactor
import org.testcontainers.utility.DockerImageName
import zio.test.Live
import zio.{Random, UIO, ULayer, ZIO, ZLayer}

import scala.util.chaining.scalaUtilChainingOps

trait DbAdmin {
    def createDatabase: UIO[DbConfig]
}

enum UpTo:
    case Version(version: DatabaseVersion)
    case Latest

type DatabaseVersion = DatabaseVersion.T

object DatabaseVersion extends RefinedType[String, Match["[0-9]{5}"]]

trait Postgres {

    def migrations: Seq[String]

    def version: String = "14"

    def migrationsPrefix: String = "sql_schemas"

    import doobie.syntax.all.*
    import zio.interop.catz.*

    def createDatabaseSql(name: String): doobie.Update0 =
        (sql"""create schema """ ++ Fragment.const(name)).update

    private val dbAdminLayer: ZLayer[Transactor & DbConfig, Nothing, DbAdmin] = ZLayer {
        for
            conf <- ZIO.service[DbConfig]
            transactor <- ZIO.service[Transactor]
            _ <-
                sql"""CREATE EXTENSION pg_trgm; CREATE EXTENSION btree_gin;""".update.run
                    .transact(transactor)
                    .orDie
        yield new DbAdmin {
            override val createDatabase: UIO[DbConfig] =
                for
                    name <- Live.live {
                        Random.nextUUID.map(_.toString.filterNot(_ == '-'))
                    }
                    schemaName = s"schema$name"
                    query = createDatabaseSql(schemaName)
                    _ <- query.run.transact(transactor).orDie
                yield conf.copy(schema = Some(schemaName))
        }
    }
    val sharedAdminConnection: ULayer[DbAdmin] =
        ZLayer.make[DbAdmin](
            PostgresqlContainer.layer,
            PostgresqlContainer.configuration,
            JdbcConnection.layer,
            dbAdminLayer
        )

    def dbConfigToTransactor(using version: UpTo): ZLayer[DbConfig, Nothing, Transactor] =
        ZLayer.makeSome[DbConfig, Transactor](
            JdbcConnection.layer.tap {
                transactor => createDatabaseTables.provideEnvironment(transactor)
            }
        )

    def createDatabaseTables(using version: UpTo) =
        for
            transactor <- ZIO.service[Transactor]
            selectedMigrations = migrations.takeUntilInclusive(migration =>
                version match {
                    case UpTo.Version(v) => migration.startsWith(v.value)
                    case UpTo.Latest     => false
                }
            )
            _ <- ZIO.foreach(selectedMigrations)(script =>
                ZIO
                    .readFile(getClass.getClassLoader.getResource(s"${migrationsPrefix}/$script").getPath)
                    .map(fragment.Fragment.const(_))
                    .flatMap(script => script.update.run.transact(transactor))
            ).orDie
        yield ()

    extension (self: Seq[String])
        private def takeUntilInclusive(predicate: String => Boolean): Seq[String] =
            self.span(input => !predicate(input)).pipe {
                case (first, Nil)       => first
                case (first, head :: _) => first.appended(head)
            }

    def transactor(using upTo: UpTo): ZLayer[DbAdmin, Nothing, Transactor] =
        ZLayer {
            for
                admin <- ZIO.service[DbAdmin]
                conf <- admin.createDatabase
            yield conf
        } >>> dbConfigToTransactor

    val transactor: ZLayer[DbAdmin, Nothing, Transactor] = transactor(using upTo = UpTo.Latest)

    object PostgresqlContainer {

        val layer: ULayer[PostgreSQLContainer] = ZLayer.scoped {
            ZIO.attemptBlocking {
                val container = PostgreSQLContainer(
                    dockerImageNameOverride = DockerImageName.parse(s"postgres:$version"),
                    mountPostgresDataToTmpfs = true
                )
                val c = container.container
                c.withCommand("postgres", "-c", "fsync=off", "-c", "max_connections=10000")
                c.start()
                container
            }.withFinalizerAuto
                .orDie
        }

        val configuration: ZLayer[PostgreSQLContainer, Nothing, DbConfig] = ZLayer {
            for container <- ZIO.service[PostgreSQLContainer]
            yield DbConfig(
                host = container.host,
                port = container.mappedPort(5432),
                name = container.databaseName,
                login = container.username,
                password = container.password,
                connectionPoolSize = 10
            )

        }
    }
}
