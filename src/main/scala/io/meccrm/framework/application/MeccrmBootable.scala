package io.meccrm.framework.application

import io.meccrm.framework.Bootable


trait MeccrmBootable extends Bootable {
  this: App =>

  //TODO: `load` config
  //TODO: `start` the Server
  override protected def boot(): Unit = {
    config.load()
    server.start()
    println("booted")
  }

  //TODO: On halt `stop` the Server
  override protected def halt(): Unit = {
    server.stop()
    print("halted")
  }

}
