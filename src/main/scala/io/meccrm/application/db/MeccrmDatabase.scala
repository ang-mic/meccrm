package io.meccrm.application.db

import io.meccrm.framework.db.slick.{Database, Connection}

/**
  * Provides access to the Meccrm database. Instances of this class run queries against
  * the database via Slick
  *
  * @param connection enables connectivity to the RDBMS
  */
class MeccrmDatabase(override val connection: Connection) extends Database {

  import io.meccrm.framework.db.slick.Schema

  override val schema = new Schema with CustomerSchema {
    val profile = connection.profile
  }

  override def ddl = {
    import schema.profile.api._
    schema.customers.schema
  }
}
