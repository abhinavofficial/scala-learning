import com.typesafe.sbt.packager.docker.ExecCmd

ThisBuild / version := "1.0"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / licenses ++= Seq(("MIT", url("http://opensource.org/licenses/MIT")))

publish/skip := true

name := "scala-learning"

lazy val calculators = project
  .dependsOn(api)
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    libraryDependencies ++= Dependencies.calculatorDependencies,
    dockerCommands := dockerCommands.value.filterNot {
      case ExecCmd("ENTRYPOINT", _) => true
      case _ => false
    },
    dockerCommands ++= Seq(ExecCmd("ENTRYPOINT", "/opt/docker/bin/net-worth"))
  )

lazy val api = project
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Dependencies.apiDependencies
  )

Global / onChangedBuildSource := ReloadOnSourceChanges