package io.meccrm.application.db

import io.meccrm.framework.db.slick.Connection

/**
  * Connection for PostgreSQL via slick
  */
object PsqlConnection extends Connection {

  import slick.jdbc.PostgresProfile.api.Database

  override val db = Database.forConfig("mydb")
  override val profile = slick.jdbc.PostgresProfile
}

