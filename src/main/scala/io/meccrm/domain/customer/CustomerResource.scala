package io.meccrm.domain.customer

import io.meccrm.framework.http.akka.Resource
import akka.http.scaladsl.server.Route
import io.meccrm.framework.RestService

trait CustomerResource extends Resource[Customer] {

  override protected val name = "customers"

  override protected val service = ???

  def customerRoutes: Route = routes
}

class CustomerRes(customerService: RestService[Customer]) extends Resource[Customer] {

  override protected val name = "customers"

  //FIXME: Find a way to inject that
  override protected val service = customerService

  def customerRoutes: Route = routes
}
