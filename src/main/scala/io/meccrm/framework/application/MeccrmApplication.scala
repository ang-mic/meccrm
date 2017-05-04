package io.meccrm.framework.application


object MeccrmApplication extends MeccrmBootable with App {
  boot()
  sys.addShutdownHook(halt())
}
