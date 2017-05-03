package io.meccrm.framework


trait Server {
  protected def start: Unit

  protected def stop: Unit
}
