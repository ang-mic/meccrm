package io.meccrm.framework


trait Bootable {
  def boot(): Unit

  def halt(): Unit
}