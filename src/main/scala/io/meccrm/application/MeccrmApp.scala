package io.meccrm.application

import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent
import io.meccrm.framework.Bootable

/**
  * [[io.meccrm.framework.ApplicationLauncher ApplicationLauncher]] doesn't work out of the box with
  * CAKE pattern
  */
object MeccrmApp extends MeccrmBootable with ComponentRegistry with App {
  sys.ShutdownHookThread(halt())
  boot()
}


/**
  * DI with parameters it is not ideal. Find out what is more convenient for testing
  * Also it doesn't work directly with the `ApplicationLauncher`
  */
class MeccrmBootable extends Bootable {
  this: ServerComponent with ConfigComponent =>

  override def boot(): Unit = {
    server.start(config.host, config.ip)
    println("Booted")
  }

  override def halt(): Unit = {
    server.stop()
    println("Halted")
  }

}
