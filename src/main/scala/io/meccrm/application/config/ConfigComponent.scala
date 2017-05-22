package io.meccrm.application.config

import com.typesafe.config.ConfigFactory
import io.meccrm.framework.Config


trait ConfigComponent {

  val config: AppConfig

  object AppConfig extends AppConfig

  class AppConfig extends Config {
    val typeSafeConfig: com.typesafe.config.Config = ConfigFactory.load()
    val host: String = typeSafeConfig.getString("host.host")
    val port: Int  = typeSafeConfig.getInt("host.port")

    //FIXME: This signature doesn't make sense, revise
    override def load(): Unit = {
      println("Config loaded")
    }
  }
}

