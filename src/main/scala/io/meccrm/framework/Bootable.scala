package io.meccrm.framework


trait Bootable {

  val config: Config
  val server: Server

  protected def boot(): Unit

  protected def halt(): Unit
}
