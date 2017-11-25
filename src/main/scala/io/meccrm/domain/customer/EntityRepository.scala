package io.meccrm.domain.customer

import io.meccrm.application.db.MeccrmDatabase
import io.meccrm.framework.EntityRepository

import scala.concurrent.Future

/**
  * Provides the CRUD operations for the [[io.meccrm.domain.customer.Customer Customer]] entity
  */
class CustomerRepository(db: MeccrmDatabase) extends EntityRepository[Customer] {

  import db.schema.profile.api._
  import db.schema.customers

  override def find(id: Int): Future[Option[Customer]] = {
    val findCustomerQuery = customers.filter(_.id === id).result.headOption
    db.runAsync(findCustomerQuery)
  }

  override def insert(customer: Customer): Future[Int] = {
    val insertCustomerAndReturnIdQuery = customers returning customers.map(_.id) += customer
    db.runAsync(insertCustomerAndReturnIdQuery)
  }

  override def update(customer: Customer): Future[Int] = {
    val updateAndReturnCustomerQuery = customers.update(customer)
    db.runAsync(updateAndReturnCustomerQuery)
  }

  override def delete(id: Int): Future[Int] = {
    val deleteCustomerByIdQuery = customers.filter(_.id === id).delete
    db.runAsync(deleteCustomerByIdQuery)
  }
}
