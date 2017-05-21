package io.meccrm.application

import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent

// FIXME: I don't like the CAKE patter for DI
trait ComponentRegistry extends ServerComponent with ConfigComponent {
  override val config: AppConfig = AppConfig
  override val server: HttpServer = HttpServer
}