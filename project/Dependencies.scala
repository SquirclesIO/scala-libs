import sbt._

object Dependencies {

    implicit class Ops(self: Seq[ModuleID]) {
        def asTest: Seq[ModuleID] = self.map(_ % Test)
    }

    val circeVersion = "0.14.15"
    val ironVersion = "3.3.0"
    val zioJsonVersion = "0.7.14"
    val zioVersion = "2.1.24"
    val doobieVersion = "1.0.0-RC11"

    val zioDependencies = Seq(
        "dev.zio" %% "zio" % zioVersion,
        "dev.zio" %% "zio-streams" % zioVersion,
        "dev.zio" %% "zio-prelude" % "1.0.0-RC46"
    )

    val zioJsonDependencies = Seq(
        "dev.zio" %% "zio-json" % zioJsonVersion,
        "io.github.iltotore" %% "iron-zio-json" % ironVersion
    )

    val circeDependencies = Seq(
        "io.circe" %% "circe-core" % circeVersion,
        "io.circe" %% "circe-generic" % circeVersion,
        "io.circe" %% "circe-parser" % circeVersion,
        "io.github.iltotore" %% "iron-circe" % ironVersion
    )

    val dbDependencies = Seq(
        "dev.zio" %% "zio-interop-cats" % "23.1.0.13",
        "org.tpolecat" %% "doobie-core" % doobieVersion,
        "org.tpolecat" %% "doobie-postgres" % doobieVersion,
        "org.tpolecat" %% "doobie-hikari" % doobieVersion
    )

    val dbTestDependencies = Seq(
        "com.dimafeng" %% "testcontainers-scala-postgresql" % "0.44.1"
    )

    val ironDependencies = Seq(
        "io.github.iltotore" %% "iron" % ironVersion
    )

    val testDependencies: Seq[ModuleID] = Seq(
        "dev.zio" %% "zio-test" % zioVersion,
        "dev.zio" %% "zio-test-sbt" % zioVersion
    )
}
