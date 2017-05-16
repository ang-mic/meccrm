package io.meccrm.application

import io.meccrm.framework.Server

object HttpServer extends HttpServer

trait HttpServer extends Server {
  override def start(): Unit = {
    println("HttpServer started")
  }

  override def stop(): Unit = {
    println("HttpServer stopped")
  }
}
