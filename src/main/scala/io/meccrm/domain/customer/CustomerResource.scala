package io.meccrm.domain.customer

import io.meccrm.framework.http.akka.Resource
import akka.http.scaladsl.server.Route

trait CustomerResource extends Resource[Customer] {

  override val resourceName = "customers"

  def customerRoutes: Route  =  routes
}
