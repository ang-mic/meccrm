package io.meccrm.application.http

import io.meccrm.domain.customer.CustomerResource

object RestInterface extends RestInterface

trait RestInterface extends CustomerResource {
  def appRoutes = customerRoutes
}
