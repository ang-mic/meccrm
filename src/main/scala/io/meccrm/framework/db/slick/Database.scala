package io.meccrm.framework.db.slick

import scala.concurrent.Future
import slick.dbio.DBIO
import slick.lifted.Query

trait Database {

  val connection: Connection
  val schema: Schema

  /** Returns the Data Definition Language of the database instance */
  def ddl: schema.profile.DDL

  def runAsync[U](action: DBIO[U]): Future[U] = connection.db.run(action)

  //TODO: Not sure if I need this one
  def executeQueryAsync[E, U, C[_]](q: Query[E, U, C]): Future[C[U]] = {
    import schema.profile.api._
    connection.db.run(q.result)
  }
}
