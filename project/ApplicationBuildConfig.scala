import sbt._
import Keys._

import Dependencies._

object ApplicationBuildConfig {
    val groupId = "org.perfimmo"
    val appName = "pi-prelude"
    val appVersion = "2.0.3"
    val versionOfScala = "2.13.16"

    val appDependencies =
        zioDependencies ++
        circeDeps ++
        testDependencies
}

object Dependencies {
    val zio_version = "2.1.14"
    val zio_json_version = "0.7.9"
    val circeVersion = "0.14.10"

    val zioDependencies = Seq(
        "dev.zio" %% "zio" % zio_version,
        "dev.zio" %% "zio-streams" % zio_version,
        "dev.zio" %% "zio-prelude" % "1.0.0-RC31",
        "dev.zio" %% "zio-json" % zio_json_version,
        "dev.zio" %% "zio-json-interop-refined" % zio_json_version
    )

    val circeDeps = Seq(
        "io.circe" %% "circe-core" % circeVersion,
        "io.circe" %% "circe-generic" % circeVersion,
        "io.circe" %% "circe-parser" % circeVersion
    )

    val testDependencies = Seq(
        "dev.zio" %% "zio-test" % zio_version % Test,
        "dev.zio" %% "zio-test-sbt" % zio_version % Test
    )
}
