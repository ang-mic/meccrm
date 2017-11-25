package io.meccrm.framework.db.slick

import slick.jdbc.JdbcProfile

trait Schema {

  /**
    * Generic representation of a JDBC driver.
    * The [[io.meccrm.framework.db.slick.Connection Connection]] will provide it.
    */
  val profile: JdbcProfile
}
