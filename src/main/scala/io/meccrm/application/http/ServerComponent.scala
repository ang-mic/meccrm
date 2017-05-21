package io.meccrm.application.http

import io.meccrm.framework.Server


trait ServerComponent {

  val server: Server

  object HttpServer extends HttpServer

  class HttpServer extends Server {

    //TODO: ip and port is need
    //TODO: Maybe `bind` is a better name
    override def start(host: String, port: Int): Unit = {
      println(s"HttpServer started at: ${host}:${port}")
    }

    override def stop(): Unit = {
      println("HttpServer stopped")
    }
  }

}
