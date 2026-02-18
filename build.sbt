import sbt.Keys.libraryDependencies
import Dependencies.{circeDependencies, ironDependencies, testDependencies, zioDependencies, zioJsonDependencies}
import org.typelevel.sbt.tpolecat.DevMode

ThisBuild / organization := "io.squircles"
ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version := "2.0.7"
ThisBuild / semanticdbEnabled := true // required for scalafix

lazy val root_project = (project in file("."))
    .settings(
        name := "lib"
    ).aggregate(`types`, `circe`, `zio-json`)

lazy val `types` = (project in file("types"))
    .settings(
        name := "lib-types",
        libraryDependencies ++= zioDependencies ++ ironDependencies ++ testDependencies
    )

lazy val `circe` = (project in file("circe"))
    .settings(
        name := "lib-circe",
        libraryDependencies ++= circeDependencies ++ testDependencies
    ).dependsOn(`types`)

lazy val `zio-json` = (project in file("zio-json"))
    .settings(
        name := "lib-zio-json",
        libraryDependencies ++= zioJsonDependencies ++ testDependencies
    ).dependsOn(`types`)

ThisBuild / credentials += Credentials(
    "Sonatype Nexus Repository Manager",
    "team.performance.immo",
    sys.env.get("NEXUS_USER").getOrElse(""),
    sys.env.get("NEXUS_PWD").getOrElse("")
)

ThisBuild / publishTo := {
    val nexus = "https://team.performance.immo/nexus/repository"

    if (isSnapshot.value) Some("snapshots" at nexus + "/maven-snapshots/")
    else Some("releases" at nexus + "/maven-releases/")
}

ThisBuild / tpolecatDefaultOptionsMode := DevMode

addCommandAlias("lint", "scalafixAll; scalafmtAll; scalafmtSbt")
