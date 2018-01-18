import Dependencies._

name := "slick-seed"

organization := "it.databiz"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= database.dependencies ++
  slick.dependencies ++
  jodaTime.dependencies ++
  logging.dependencies
