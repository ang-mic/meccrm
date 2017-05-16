package io.meccrm.application

import io.meccrm.framework.{Config, Registry, Server}


trait ComponentRegistry extends Registry {
  override val config: Config = AppConfig
  override val server: Server = HttpServer
}
