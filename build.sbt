name := "slick-seed"

organization := "it.databiz"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  /* Database access related dependencies */
  "com.h2database" % "h2" % "1.4.196",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
  "com.jolbox" % "bonecp" % "0.8.0.RELEASE",
  /* Logging */
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  /* Testing */
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  /* Utilities */
  "com.typesafe" % "config" % "1.3.2",
  "joda-time" % "joda-time" % "2.9.9",
  "org.joda" % "joda-convert" % "1.9.2"
)
