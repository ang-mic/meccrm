package io.meccrm.domain.customer

import io.meccrm.framework.http.akka.{Resource, RestService}

object CustomerResource {
  def apply(customerService: RestService[Customer]): CustomerResource = new CustomerResource(customerService)
}

final class CustomerResource(customerService: RestService[Customer]) extends Resource[Customer] {

  override protected val name = "customers"

  override protected val service = customerService
}
