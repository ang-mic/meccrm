package io.meccrm.application

import io.meccrm.framework.{ApplicationLauncher, Bootable, Config, Server}


object MeccrmApplication extends ApplicationLauncher[MeccrmApplication]

/**
  * DI with parameters it is not ideal. Find out what is more convenient for testing
  * Also it doesn't work directly with the `ApplicationLauncher`
  */
class MeccrmApplication extends Bootable with ComponentRegistry {

  override def boot(): Unit = {
    server.start()
    println("Booted")
  }

  override def halt(): Unit = {
    server.stop()
    println("Halted")
  }

}
