package io.meccrm.application

import com.typesafe.scalalogging.LazyLogging
import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent
import io.meccrm.framework.Bootable

object MeccrmApp extends MeccrmBootable with ComponentRegistry with App {
  sys.ShutdownHookThread(halt())
  boot()
}

class MeccrmBootable extends Bootable with LazyLogging {
  this: ServerComponent with ConfigComponent =>

  override def boot(): Unit = {
    logger.info("Booting")
    server.start(config.host, config.port)
  }

  override def halt(): Unit = {
    server.stop()
    logger.info("Halted")
  }

}
