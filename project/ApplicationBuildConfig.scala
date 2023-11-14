import sbt._
import Keys._

import Dependencies._

object ApplicationBuildConfig {
    val groupId = "org.perfimmo"
    val appName = "pi-prelude"
    val appVersion = "2.0.0"
    val versionOfScala = "2.13.12"

    val appDependencies =
        zioDependencies ++
        circeDeps ++
        testDependencies
}

object Dependencies {
    val zio_version = "2.0.19"
    val zio_prelude_version = "1.0.0-RC21"
    val circeVersion = "0.14.6"

    val zioDependencies = Seq(
        "dev.zio" %% "zio" % zio_version,
        "dev.zio" %% "zio-streams" % zio_version,
        "dev.zio" %% "zio-prelude" % zio_prelude_version,
        "dev.zio" %% "zio-json" % "0.6.2",
        "dev.zio" %% "zio-json-interop-refined" % "0.6.2"
    )

    val circeDeps = Seq(
        "io.circe" %% "circe-core",
        "io.circe" %% "circe-generic",
        "io.circe" %% "circe-parser"
    ).map(_ % circeVersion)

    val testDependencies = Seq(
        "org.scalatest" %% "scalatest" % "3.2.17" % Test
    )
}
