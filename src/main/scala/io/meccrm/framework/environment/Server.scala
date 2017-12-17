package io.meccrm.framework.environment

abstract class Server(host: String, port: Int) {
  def start(): Unit

  def stop(): Unit
}
