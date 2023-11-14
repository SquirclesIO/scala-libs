import sbt._
import Keys._

import Dependencies._

object ApplicationBuildConfig {
	val groupId = "org.perfimmo"
	val appName = "pi-prelude"
	val appVersion = "1.0.1"
	val versionOfScala = "2.13.12"

	val appDependencies =
		zioDependencies ++
		javaDepencencies ++
		circeDeps ++
		testDependencies

}

object Dependencies {
	val zio_version = "2.0.19"
	val zio_prelude_version = "1.0.0-RC21"
	val circeVersion = "0.14.6"

	val zioDependencies = Seq(
		"dev.zio"                           %% "zio"                                % zio_version,
		"dev.zio"                           %% "zio-streams"                        % zio_version,
		//"dev.zio"                         %% "zio-interop-reactivestreams"                    % "1.0.3.5",
		"dev.zio"                           %% "zio-prelude"                        % zio_prelude_version,
		"dev.zio"                           %% "zio-json"                           % "0.6.2",
		"dev.zio" 							            %% "zio-json-interop-refined" 			    % "0.6.2"
	)

	val circeDeps = Seq(
		"io.circe" %% "circe-core",
		"io.circe" %% "circe-generic",
		"io.circe" %% "circe-parser"
	).map(_ % circeVersion)


	val javaDepencencies = Seq(
	)

	val testDependencies = Seq (
		"org.scalatest"                     %% "scalatest"                          % "3.2.0"           % "test"
	)

	/*
		/** not exhaustive match error plugin */
libraryDependencies ++= Seq(
	compilerPlugin("com.softwaremill.neme" %% "neme-plugin" % "0.0.5")
)
	 */
}