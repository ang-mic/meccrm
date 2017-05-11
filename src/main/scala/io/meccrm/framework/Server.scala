package io.meccrm.framework


trait Server {
  def start(): Unit

  def stop(): Unit
}
