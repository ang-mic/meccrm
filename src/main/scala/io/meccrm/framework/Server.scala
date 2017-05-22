package io.meccrm.framework

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer


trait Server {
  def start(host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer): Unit

  def stop(): Unit
}
