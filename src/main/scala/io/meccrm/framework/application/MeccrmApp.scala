package io.meccrm.framework.application

import io.meccrm.framework.{ApplicationLauncher, Bootable, Config, Server}


object MeccrmApplication extends ApplicationLauncher[MeccrmApplication]

/**
  * DI with parameters it is not ideal. Find out what is more convenient for testing
  * Also it doesn't work directly with the `ApplicationLauncher`
  */
class MeccrmApplication(appConfig: Config, httpServer: Server) extends Bootable {

  override def boot(): Unit = {
    httpServer.start()
    println("Booted")
  }

  override def halt(): Unit = {
    httpServer.stop()
    println("Halted")
  }

}
