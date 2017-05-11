package io.meccrm.framework.application

import io.meccrm.framework.Config

trait AppConfig extends Config {
  override def load(): Unit = {
    println("Config loaded")
  }
}
