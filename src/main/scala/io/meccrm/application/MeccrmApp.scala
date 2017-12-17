package io.meccrm.application

import com.typesafe.scalalogging.LazyLogging
import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent
import io.meccrm.framework.environment.Bootable

object MeccrmApp extends MeccrmBootable with ProductionWebService with App {
  sys.ShutdownHookThread(halt())
  boot()
}

trait MeccrmBootable extends Bootable with LazyLogging {
  this: ServerComponent =>

  override def boot(): Unit = {
    logger.info("Booting")
    server.start()
  }

  override def halt(): Unit = {
    server.stop()
    logger.info("Halted")
  }

}
