import sbt.Keys.libraryDependencies
import ApplicationBuildConfig._

lazy val must_back = (project in file("."))
	.settings(
		organization := groupId,
		name := appName,
		version := appVersion,
		scalaVersion := versionOfScala,

		scalacOptions ++= Seq("-language:postfixOps"),
		scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked"),
		scalacOptions ++= Seq("-Xmaxerrs", "1000"),
		scalacOptions ++= Seq("-Xmaxwarns", "1000"),

		libraryDependencies ++= appDependencies
	)

	//enablePlugins(JavaAppPackaging)

	// Disable javadoc packaging
	mappings in (Compile, packageDoc) := Seq()
