package io.meccrm.framework

import scala.concurrent.Future

/**
  * Encapsulates the CRUD operations for the entities
  *
  * @tparam E an entity which need to be persisted in the database
  */
//TODO: Find a way to write generic DB function using slick's TableQuery
trait EntityRepository[E] {

  def find(id: Int): Future[Option[E]]

  def insert(entity: E): Future[Int]

  def update(entity: E): Future[Int]

  def delete(id: Int): Future[Int]
}
