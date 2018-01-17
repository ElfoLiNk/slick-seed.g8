package it.databiz.slick.seed

import com.typesafe.scalalogging.Logger
import it.databiz.slick.seed.Users._
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.language.reflectiveCalls
import scala.util.Random

object Main extends App {

  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  val dbConfig = DatabaseConfig.forConfig[JdbcProfile]("default")
  val db = dbConfig.db
  import dbConfig.profile.api._

  val MaxUserCount = 100

  val res = db.run((for {
    _ <- users.schema.create
    _ = users.schema.createStatements.foreach(x => logger info s"DDL statement executed: $x")
    _ <- users ++= (0 until Random.nextInt(MaxUserCount)).map(i => User(None, s"example$i@example.com", "password", DateTime.now(), false))
    count <- users.count.result
    _ = logger.info(s"There are $count users in the database")
    emailToFind = s"example${Random.nextInt(MaxUserCount)}@example.com"
    regTime <- users.findByEmail(emailToFind).result.map(_.headOption.map(x => x.registredAt).getOrElse("Not yet"))
    _ = logger.info(s"User with email '$emailToFind' has registered at: $regTime")
    _ = users.schema.dropStatements.foreach(x => logger info s"DROP statement executed: $x")
    _ <- users.schema.drop
  } yield ()).transactionally)

  Await.result(res, Duration.Inf)

}

