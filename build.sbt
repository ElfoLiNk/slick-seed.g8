name := "slick-seed"

organization := "it.databiz"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.196",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "joda-time" % "joda-time" % "2.9.9"
)
