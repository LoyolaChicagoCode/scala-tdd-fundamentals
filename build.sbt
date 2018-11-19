name := "scala-tdd-fundamentals"

version := "1.0.2"

scalaVersion := "2.11.12"

scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "junit" % "junit" % "4.11" % Test
)
