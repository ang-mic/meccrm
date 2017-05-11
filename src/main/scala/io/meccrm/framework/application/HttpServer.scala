package io.meccrm.framework.application

import io.meccrm.framework.Server

trait HttpServer extends Server {
  override def start(): Unit = {
    println("HttpServer started")
  }

  override def stop(): Unit = {
    println("HttpServer stopped")
  }
}
