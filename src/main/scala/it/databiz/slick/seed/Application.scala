package it.databiz.slick.seed

import com.typesafe.config.{Config, ConfigFactory}
import com.jolbox.bonecp.{BoneCPDataSource, BoneCPConfig}
import slick.jdbc.JdbcBackend.Database

object Application {
  private val DefaultDatabaseName = "default"

  lazy val configuration: Config = ConfigFactory.load()

  lazy val db = {
    val connectionPoolConfig = new BoneCPConfig
    connectionPoolConfig.setJdbcUrl(configuration.getString(s"db.$DefaultDatabaseName.jdbcUri"))
    connectionPoolConfig.setUsername(configuration.getString(s"db.$DefaultDatabaseName.username"))
    connectionPoolConfig.setPassword(configuration.getString(s"db.$DefaultDatabaseName.password"))
    connectionPoolConfig.setPartitionCount(configuration.getInt(s"db.$DefaultDatabaseName.pool.partitionCount"))
    connectionPoolConfig.setMinConnectionsPerPartition(configuration.getInt(s"db.$DefaultDatabaseName.pool.minConnectionsPerPartition"))
    connectionPoolConfig.setMaxConnectionsPerPartition(configuration.getInt(s"db.$DefaultDatabaseName.pool.maxConnectionsPerPartition"))
    Database.forDataSource(new BoneCPDataSource(connectionPoolConfig), None)
  }
}
