package io.meccrm.application

import com.typesafe.config.ConfigFactory
import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.{ServerComponent, RestInterface}


// FIXME: I don't like the CAKE patter for DI
// NOTE: Try to do DI with implicits, there is a pattern for that
trait ComponentRegistry extends ServerComponent with ConfigComponent {
  //NOTE: it might be better to load it from env trait
  //NOTE: Not sure if refering to Config as component is right
  override val config: AppConfig  = AppConfig(ConfigFactory.load())
  override val server: HttpServer = new HttpServer(RestInterface.appRoutes)
}