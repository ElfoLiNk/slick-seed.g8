package it.databiz.slick.seed

import Application.db
import Users._
import slick.jdbc.H2Profile.api._
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Random
import scala.language.reflectiveCalls

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  val logger = Logger(LoggerFactory.getLogger(this.getClass))
  val MaxUserCount = 100

  val random = new Random

  val res = db.run((for {
    _ <- users.schema.create
    _ = users.schema.createStatements.foreach(x => logger info s"DDL statement executed: $x")
    _ <- users ++= (0 until random.nextInt(MaxUserCount)).map(i => User(None, s"example$i@example.com", "password", DateTime.now(), false))
    count <- users.count.result
    _ = logger.info(s"There are $count users in the database")
    emailToFind = s"example${random.nextInt(MaxUserCount)}@example.com"
    regTime <- users.findByEmail(emailToFind).result.map(_.headOption.map(x => x.registredAt).getOrElse("Not yet"))
    _ = logger.info(s"User with email '$emailToFind' has registered at: $regTime")
    _ = users.schema.dropStatements.foreach(x => logger info s"DROP statement executed: $x")
    _ <- users.schema.drop
  } yield ()).transactionally)

  Await.result(res, Duration.Inf)

}

