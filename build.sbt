import sbt.Keys.libraryDependencies
import Dependencies.{circeDependencies, ironDependencies, testDependencies, zioDependencies, zioJsonDependencies}
import org.typelevel.sbt.tpolecat.DevMode

ThisBuild / organization := "io.squircles"
ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version := sys.env.get("VERSION").getOrElse("0.0.1-SNAPSHOT")
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

ThisBuild / publishTo := Some("GitHub SquirclesIO Apache Maven Packages" at "https://maven.pkg.github.com/SquirclesIO/scala-libs")
ThisBuild / publishMavenStyle := true
ThisBuild / credentials += Credentials(
    "GitHub Package Registry",
    "maven.pkg.github.com",
    "SquirclesIO",
    sys.env.get("GITHUB_TOKEN").getOrElse("")
)

ThisBuild / tpolecatDefaultOptionsMode := DevMode

addCommandAlias("lint", "scalafixAll; scalafmtAll; scalafmtSbt")
addCommandAlias("check", "scalafmtCheckAll; scalafmtSbtCheck")
