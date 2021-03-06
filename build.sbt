import sbt.Project.projectToRef

name := "tick-tack-toe"

lazy val clients = Seq(client)
lazy val scalaV = "2.11.8"

scalaJSUseRhino in Global := false

lazy val server = (project in file("server")).settings(
	scalaVersion := scalaV,
	scalaJSProjects := clients,
	pipelineStages := Seq(scalaJSProd, gzip),
	libraryDependencies ++= Seq(
	),
  testFrameworks += new TestFramework("utest.runner.Framework")
).enablePlugins(PlayScala).
  aggregate(projectToRef(sharedJvm)).
  dependsOn(sharedJvm)

lazy val client = (project in file("client")).settings(
	scalaVersion := scalaV,
	persistLauncher := true,
	persistLauncher in Test := false,
	libraryDependencies ++= Seq(
		"org.scala-js" %%% "scalajs-dom" % "0.8.0"
	),
  testFrameworks += new TestFramework("utest.runner.Framework")
).enablePlugins(ScalaJSPlugin, ScalaJSPlay).
  aggregate(projectToRef(sharedJs)).
  dependsOn(sharedJs)


lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
	settings(
		scalaVersion := scalaV,
		libraryDependencies ++= Seq(
			"com.lihaoyi" %%% "scalatags" % "0.4.6",
			"com.lihaoyi" %%% "upickle" % "0.2.7",
			"com.lihaoyi" %%% "autowire" % "0.2.5",
			"com.lihaoyi" %%% "utest" % "0.4.3"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
	).jsConfigure(_ enablePlugins ScalaJSPlay)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// loads the Play project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
