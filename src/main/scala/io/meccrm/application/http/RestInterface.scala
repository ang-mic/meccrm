package io.meccrm.application.http

import io.meccrm.domain.customer.{CustomerRes, CustomerResource}

object RestInterface extends RestInterface

trait RestInterface extends CustomerResource {
  def appRoutes = customerRoutes
}

class RestInterface2(customerResource: CustomerRes) {
  def appRoutes = customerResource.customerRoutes
}
