package io.meccrm.framework

trait Server {
  def start(host: String, port: Int): Unit

  def stop(): Unit
}
