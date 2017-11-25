package io.meccrm.framework.db.slick

import slick.jdbc.JdbcProfile

/**
  * Abstracts representation of a database access via Slick
  */
trait Connection {

  /** Slick's representation of database access */
  val db: profile.Backend#DatabaseDef

  /**
    * Generic representation of a JDBC driver. Its
    * implementation can be MySql driver, PostgreSql driver etc.
    */
  val profile: JdbcProfile
}
