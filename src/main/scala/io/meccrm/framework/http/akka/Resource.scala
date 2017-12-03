package io.meccrm.framework.http.akka

import akka.http.scaladsl.server.{Directives, Route}
import io.meccrm.framework.Entity

//TODO: Make sure that the naming makes sense and uses the right conventions
///NOTE: Not sure if protected is the right visibility for everything
abstract class Resource[E <: Entity] extends Directives {

  protected val name: String

  protected def routes: Route = resourcePrefix(list ~ create ~ read ~ update ~ deleteResource)

  protected def resourcePrefix(paths: Route): Route = pathPrefix(name)(paths)

  protected def list: Route = {
    pathEnd {
      get {
        complete(s"Here you are, all your $name")
      }
    }
  }

  protected def create: Route = {
    pathEnd {
      post {
        entity(as[String]) { newResource =>
          complete(s"A new $name has been created")
        }
      }
    }
  }

  protected def read: Route = {
    path(Segment) { resourceId =>
      get {
        complete(s"Here is your $name - $resourceId")
      }
    }
  }

  protected def update: Route = {
    path(Segment) { resourceId =>
      put {
        entity(as[String]) { updateResource =>
          complete(s"The $name - $resourceId has been updated")
        }
      }
    }
  }

  /** name delete is reserved from Directives trait */
  protected def deleteResource: Route = {
    path(Segment) { resourceId =>
      delete {
        complete(s"The $name - $resourceId has been deleted")
      }
    }
  }

}