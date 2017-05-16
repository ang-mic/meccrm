package io.meccrm.application

import io.meccrm.framework.Config

object AppConfig extends AppConfig

trait AppConfig extends Config {
  override def load(): Unit = {
    println("Config loaded")
  }
}
