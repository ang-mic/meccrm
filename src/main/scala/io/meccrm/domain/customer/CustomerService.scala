package io.meccrm.domain.customer

import io.meccrm.framework.http.akka.RestService


class CustomerService extends RestService[Customer] {
  override def getAll: Seq[Customer] = Seq(Customer(None, "ang", "mic", "co"))

  override def getById(id: Int): Customer = Customer(Some(id), "ang", "mic", "co")

  override def create(entity: Customer): Int = 1

  override def update(id: Int, entity: Customer): Int = id

  override def delete(id: Int): Boolean = true
}
