package io.meccrm.framework.http.akka

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import io.meccrm.framework.Entity
import org.json4s._

//TODO: Make sure that the naming makes sense and uses the right conventions
//TODO: Find the right JSON library
///NOTE: Not sure if protected is the right visibility for everything
//  protected def routes: Route = resourcePrefix(list ~ create ~ retrieve ~ update ~ deleteResource)
//  protected def resourcePrefix(paths: Route): Route = pathPrefix(name)(paths)
//FIXME: Find a way to make it trait
abstract class Resource[E <: Entity : Manifest] extends Json4sSupport {

  //TODO: Move it JSON support
  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

  protected val name: String
  protected val service: RestService[E]

  def routes: Route = pathPrefix(name)(list ~ create ~ retrieve ~ update ~ deleteResource)

  protected def list: Route = {
    pathEnd {
      get {
        complete(service.getAll)
      }
    }
  }

  protected def create: Route = {
    pathEnd {
      post {
        entity(as[E]) { newResource =>
          complete(("resourceId" -> service.create(newResource)))
        }
      }
    }
  }

  protected def retrieve: Route = {
    path(IntNumber) { resourceId =>
      get {
        complete(service.getById(resourceId))
      }
    }
  }

  protected def update: Route = {
    path(IntNumber) { resourceId =>
      put {
        entity(as[E]) { updateResource =>
          complete(("resourceId" -> service.update(resourceId, updateResource)))
        }
      }
    }
  }

  /** name delete is reserved from Directives trait */
  protected def deleteResource: Route = {
    path(IntNumber) { resourceId =>
      delete {
        complete(("deleted" -> service.delete(resourceId)))
      }
    }
  }
}

trait RestService[E <: Entity] {
  def getAll: Seq[E]

  def getById(id: Int): E

  def create(entity: E): Int

  def update(id: Int, entity: E): Int

  def delete(id: Int): Boolean
}
