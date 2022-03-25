import sbt._

object Dependencies {

  val scalaRequests = "com.lihaoyi" % "requests_2.13" % "0.7.0"
  val scalaXml = "org.scala-lang.modules" % "scala-xml_2.13" % "2.0.1"
  val scalaTest = "org.scalatest" % "scalatest_2.13" % "3.2.11"
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.2.9"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.6.18"
  val json4s = "org.json4s" %% "json4s-native" % "4.0.4"

  val commonDependencies: Seq[ModuleID] = Seq(scalaTest % Test)

  val apiDependencies: Seq[ModuleID] = Seq(scalaRequests,
    scalaXml, json4s, akkaHttp, akkaStream) ++ commonDependencies

  val calculatorDependencies: Seq[ModuleID] = commonDependencies
}