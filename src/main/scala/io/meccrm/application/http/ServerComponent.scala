package io.meccrm.application.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import io.meccrm.framework.Server

import scala.concurrent.ExecutionContext
import scala.util.Success
import com.typesafe.scalalogging.LazyLogging
import akka.http.scaladsl.server.Route

import scala.io.StdIn

trait ServerComponent {


  val server: Server

  implicit val system       = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec           = scala.concurrent.ExecutionContext.Implicits.global

  class HttpServer(routes: Route)(implicit ec: ExecutionContext, system: ActorSystem, materializer: ActorMaterializer) extends Server with LazyLogging {

    //TODO: ip and port is need
    //TODO: Maybe `bind` is a better name
    //TODO: Maybe return a future of Server status
    override def start(host: String, port: Int): Unit = {

      val bindingFuture = Http().bindAndHandle(routes, "0.0.0.0", port = port)
      println(s"HttpServer started at: ${host}:${port}")
      println(s"Press RETURN to stop...")
      StdIn.readLine() // let it run until user presses return
      bindingFuture
        .flatMap(_.unbind()) // trigger unbinding from the port
        .onComplete(_ => system.terminate()) // and shutdown when done
    }

    override def stop(): Unit = system.terminate andThen { case Success(_) => logger.info("HttpServer stopped") }
  }
}
