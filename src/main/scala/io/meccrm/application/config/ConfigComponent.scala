package io.meccrm.application.config

import io.meccrm.framework.Config


trait ConfigComponent {

  val config: AppConfig

  object AppConfig extends AppConfig

  trait AppConfig extends Config {

    val host: String = "localhost"
    val ip: Int      = 1234

    //FIXME: This signature doesn't make sense, revise
    override def load(): Unit = {
      println("Config loaded")
    }
  }
}

