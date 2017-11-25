package io.meccrm.application.db

import io.meccrm.domain.customer.Customer
import io.meccrm.framework.db.slick.Schema

trait CustomerSchema {
  this: Schema =>

  import profile.api._

  class Customers(tag: Tag) extends Table[Customer](tag, "customer") {
    def id        = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("first_name")
    def lastName  = column[String]("last_name")
    def company   = column[String]("company")
    def * = (id.?, firstName, lastName, company) <> (Customer.tupled, Customer.unapply)
  }

  val customers = TableQuery[Customers]
}
