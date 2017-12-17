package io.meccrm.application

import akka.http.scaladsl.server.Route
import com.typesafe.config.ConfigFactory
import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent
import io.meccrm.application.http.WebInterface.{RestfulInterface, WebInterface}
import io.meccrm.domain.customer.{Customer, CustomerResource, CustomerService}
import io.meccrm.framework.environment.WebService
import io.meccrm.framework.http.akka.Resource

trait ProductionWebService extends WebService with ComponentRegistry with ServerComponent with ConfigComponent {

  override val webInterface = new WebInterface with RestfulInterface {
    override protected val customerResource: Resource[Customer] = customerRes
    override def routes: Route = customerResource.routes
  }

  //NOTE: it might be better to load it from env trait
  //NOTE: Not sure if referring to Config as component is right
  override val config = AppConfig(ConfigFactory.load())
  override val server = new HttpServer(config.host, config.port, webInterface.routes)

}

// FIXME: I don't like the CAKE patter for DI
// NOTE: Try to do DI with implicits, there is a pattern for that
trait ComponentRegistry {
  val customerService = new CustomerService
  val customerRes = CustomerResource(customerService)
}