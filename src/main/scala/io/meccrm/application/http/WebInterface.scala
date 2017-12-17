package io.meccrm.application.http

import akka.http.scaladsl.server.Route
import io.meccrm.domain.customer.Customer
import io.meccrm.framework.http.akka.Resource

object WebInterface {

  trait WebInterface {
    def routes: Route
  }

  trait RestfulInterface {
    protected val customerResource: Resource[Customer]
    // ...
    //More resources in here

    // Aggregate the restful routes
    protected def restfulRoutes: Route = customerResource.routes
  }

}

