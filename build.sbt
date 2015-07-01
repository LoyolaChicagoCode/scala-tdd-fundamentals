name := "scala-tdd-fundamentals"

version := "1.0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % Test
