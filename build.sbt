import Dependencies._

name := "slick-seed"

organization := "it.databiz"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.4"

flywayUrl := "jdbc:h2:mem:slick-example"

flywayUser := "db"

libraryDependencies ++= database.dependencies ++
  slick.dependencies ++
  jodaTime.dependencies ++
  logging.dependencies
