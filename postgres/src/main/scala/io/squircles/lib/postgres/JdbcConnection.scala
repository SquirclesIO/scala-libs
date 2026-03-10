package io.squircles.lib.postgres

import com.zaxxer.hikari.HikariConfig
import doobie.hikari.HikariTransactor
import doobie.util.{transactor, ExecutionContexts}
import zio._
import zio.interop.catz._

object JdbcConnection {

    type Transactor = transactor.Transactor[Task]

    val layer: URLayer[DbConfig, Transactor] = {
        ZLayer.scoped {
            for
                hconfig <- ZIO.serviceWith[DbConfig] { dbConfig =>
                    val hconfig = new HikariConfig
                    hconfig.setDriverClassName("org.postgresql.Driver")
                    hconfig.setJdbcUrl(s"jdbc:postgresql://${dbConfig.host}:${dbConfig.port}/${dbConfig.name}")
                    hconfig.setUsername(dbConfig.login)
                    hconfig.setPassword(dbConfig.password)
                    hconfig.setMaximumPoolSize(dbConfig.connectionPoolSize)
                    dbConfig.schema.foreach(schema => hconfig.setSchema(schema))

                    hconfig
                }
                ce <- ExecutionContexts.fixedThreadPool[Task](hconfig.getMaximumPoolSize)
                    .toScopedZIO
                    .orDie
                pool <- HikariTransactor.fromHikariConfigCustomEc[Task](
                    hconfig,
                    ce
                    // DEBUG: log des requetes SQL
                    /*
                    logHandler = Some(new LogHandler[Task] {
                        def run(logEvent: LogEvent): Task[Unit] = ZIO.debug(logEvent.sql)
                    })
                     */
                ).toScopedZIO.orDie
            yield pool
        }
    }

}
