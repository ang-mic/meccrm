package io.meccrm.application.config

import io.meccrm.framework.Config


trait ConfigComponent {

  val config: AppConfig

  class AppConfig(val host: String, val port: Int) extends Config {
    //FIXME: This signature doesn't make sense, revise
    override def load(): Unit = {
      println("Config loaded")
    }
  }

  object AppConfig {
    def apply(typeSafeConfig: com.typesafe.config.Config): AppConfig = new AppConfig(
      host = typeSafeConfig.getString("host.host"),
      port = typeSafeConfig.getInt("host.port")
    )
  }

}

