package io.meccrm.application.db

import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway


object Flyway extends App {
  val config = ConfigFactory.load()

//  val flyway: Flyway = new Flyway
//  flyway.getDataSource(
//    config.getString("host.host"),
//    config.getString("host.host"),
//    config.getString("host.host")
//  )
}
