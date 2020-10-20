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

	credentials += Credentials("Sonatype Nexus Repository Manager", "team.performance.immo", sys.env.get("NEXUS_USER").getOrElse(""), sys.env.get("NEXUS_PWD").getOrElse(""))

	publishTo := {
		val nexus = "https://team.performance.immo/nexus/repository"
		
		if (isSnapshot.value) Some("snapshots"  at nexus + "/maven-snapshots/")
		else Some("releases" at nexus + "/maven-releases/")
	}