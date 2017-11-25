package io.meccrm.domain.customer

import org.scalatest.Matchers
import io.meccrm.util.H2TestUtil.AsyncH2Spec

import scala.concurrent.Future
import io.meccrm.application.db.MeccrmDatabase

class CustomerRepositorySpec extends AsyncH2Spec with Matchers {

  behavior of "UserRepository"

  it should "find existing Customer by ID" in {
    val customerRepo = new CustomerRepository(db)

    val firstCustomer = Customer(firstName = "Bob", lastName = "Marley", company = "HP")
    val secondCustomer = Customer(firstName = "John", lastName = "Travolta", company = "HBO")

    withCustomers(db, firstCustomer, secondCustomer) {
      for {
        c1 <- customerRepo.find(id = 1)
        c2 <- customerRepo.find(id = 2)
      } yield {
        c1 shouldBe Some(Customer(id = Some(1), firstName = "Bob", lastName = "Marley", company = "HP"))
        c2 shouldBe Some(Customer(id = Some(2), firstName = "John", lastName = "Travolta", company = "HBO"))
      }
    }
  }

  it should "insert new Customer to database and return its ID, when insertion is successful" in {
    val customerRepo = new CustomerRepository(db)

    for {
      c1 <- customerRepo.insert(Customer(firstName = "Bob", lastName = "Marley", company = "HP"))
      c2 <- customerRepo.insert(Customer(firstName = "John", lastName = "Travolta", company = "HBO"))
    } yield {
      c1 shouldEqual 1
      c2 shouldEqual 2
    }
  }

  it should "update existing Customer" in {
    val customerRepo = new CustomerRepository(db)

    val firstCustomer = Customer(firstName = "Bob", lastName = "Marley", company = "HP")

    withCustomers(db, firstCustomer) {
      val updateResult = customerRepo.update(firstCustomer.copy(id = Some(1), company = "HBO"))
      updateResult.map(_ shouldEqual 1)
    }
  }

  it should "delete existing Customer" in {
    val customerRepo = new CustomerRepository(db)

    val firstCustomer = Customer(firstName = "Bob", lastName = "Marley", company = "HP")
    val secondCustomer = Customer(firstName = "John", lastName = "Travolta", company = "HBO")

    withCustomers(db, firstCustomer, secondCustomer) {
      val deleteResult = customerRepo.delete(id = 2)
      deleteResult.map(_ shouldBe 1)
    }
  }

  private def withCustomers[A](dbTest: MeccrmDatabase, customers: Customer*)(block: => Future[A]): Future[A] = {
    import dbTest.schema.profile.api._
    dbTest.runAsync(dbTest.schema.customers ++= customers).flatMap(_ => block)
  }
}
