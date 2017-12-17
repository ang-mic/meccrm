package io.meccrm.framework.environment

trait Bootable {
  def boot(): Unit

  def halt(): Unit
}