import sbt._
import sbt.Keys._

val funDependencies = Seq(
  "com.danielasfregola" %% "random-data-generator" % "2.1",
  "com.chuusai" %% "shapeless" % "2.3.2",
  "joda-time" % "joda-time" % "2.9.9",
  "io.circe" %% "circe-core" % "0.8.0",
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-parser" % "0.8.0",
  "io.circe" %% "circe-yaml" % "0.6.1"
)

lazy val root = (project in file("."))
  .settings(Commons.settings: _*)
  .settings(libraryDependencies ++= funDependencies)

scalafmtOnCompile in ThisBuild := true

// make run command include the provided dependencies
run in Compile := Defaults.runTask(fullClasspath in Compile,
                                   mainClass in (Compile, run),
                                   runner in (Compile, run))

// exclude Scala library from assembly
assemblyOption in assembly := (assemblyOption in assembly).value
  .copy(includeScala = false)
