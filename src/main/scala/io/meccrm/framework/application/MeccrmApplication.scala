package io.meccrm.framework.application

import io.meccrm.framework.{Bootable, ApplicationLauncher}


object MeccrmApplication extends ApplicationLauncher[MeccrmApplication]

class MeccrmApplication extends Bootable {

  override def boot(): Unit = println("Booted")

  override def halt(): Unit = println("Halted")

}
