package io.meccrm.framework


trait Bootable {
  protected def boot: Unit

  protected def halt: Unit
}
