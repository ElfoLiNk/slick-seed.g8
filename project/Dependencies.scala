import sbt._
import scala.collection.immutable.Seq

object Dependencies {

  trait SimpleDependencies {
    def dependencies: Seq[ModuleID]
  }

  trait StructuredDependencies extends SimpleDependencies {
    def organization: String

    def revision: String

    def names: Seq[String]

    def dependencies: Seq[ModuleID] = names.map(organization %% _ % revision)
  }

  object database extends SimpleDependencies {
    lazy val dependencies: Seq[ModuleID] = Seq("com.h2database" % "h2" % "1.4.196")
  }

  object slick extends StructuredDependencies {
    lazy val organization: String = "com.typesafe.slick"
    lazy val revision: String = "3.2.1"
    lazy val names: Seq[String] = Seq(
      "slick",
      "slick-hikaricp"
    )
  }

  object jodaTime extends SimpleDependencies {
    lazy val dependencies: Seq[ModuleID] = Seq(
      "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
      "joda-time" % "joda-time" % "2.9.9"
    )
  }

  object logging extends SimpleDependencies {
    lazy val dependencies: Seq[ModuleID] = Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  }

}