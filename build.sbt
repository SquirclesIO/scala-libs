import sbt.Keys.libraryDependencies
import Dependencies.{
    circeDependencies,
    dbDependencies,
    dbTestDependencies,
    ironDependencies,
    testDependencies,
    zioDependencies,
    zioJsonDependencies,
    Ops
}
import org.typelevel.sbt.tpolecat.DevMode

ThisBuild / organization := "io.squircles"
ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version := sys.env.get("VERSION").getOrElse("0.0.1-SNAPSHOT")
ThisBuild / semanticdbEnabled := true // required for scalafix

lazy val root_project = (project in file("."))
    .settings(
        name := "lib"
    ).aggregate(`types`, `circe`, `zio-json`, `postgres`, `postgres-test`)

lazy val `types` = (project in file("types"))
    .settings(
        name := "lib-types",
        libraryDependencies ++= zioDependencies ++ ironDependencies ++ testDependencies.asTest
    )

lazy val `circe` = (project in file("circe"))
    .settings(
        name := "lib-circe",
        libraryDependencies ++= circeDependencies ++ testDependencies.asTest
    ).dependsOn(`types`)

lazy val `zio-json` = (project in file("zio-json"))
    .settings(
        name := "lib-zio-json",
        libraryDependencies ++= zioJsonDependencies ++ testDependencies.asTest
    ).dependsOn(`types`)

lazy val `postgres` = (project in file("postgres"))
    .settings(
        name := "lib-postgres",
        libraryDependencies ++= dbDependencies ++ testDependencies.asTest
    )

lazy val `postgres-test` = (project in file("postgres-test"))
    .settings(
        name := "lib-postgres-test",
        libraryDependencies ++= ironDependencies ++ dbDependencies ++ testDependencies ++ dbTestDependencies
    ).dependsOn(`postgres`)

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
