package io.meccrm.application.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import io.meccrm.framework.Server

import scala.io.StdIn

trait ServerComponent {

  val server: Server

  object HttpServer extends HttpServer

  class HttpServer extends Server {
    import scala.concurrent.ExecutionContext.Implicits.global

    //TODO: ip and port is need
    //TODO: Maybe `bind` is a better name
    override def start(host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer): Unit = {
      val route =
        path("hello") {
          get {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
          }
        }

      val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", port = port)
      println(s"HttpServer started at: ${host}:${port}")
      println(s"Press RETURN to stop...")
      StdIn.readLine() // let it run until user presses return
      bindingFuture
        .flatMap(_.unbind()) // trigger unbinding from the port
        .onComplete(_ => system.terminate()) // and shutdown when done
    }

    override def stop(): Unit = {
      println("HttpServer stopped")
    }
  }

}
