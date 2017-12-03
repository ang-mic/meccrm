package io.meccrm.framework.http.akka
import scala.reflect.{ClassTag, classTag}
import akka.http.scaladsl.server.{Directives, Route}

abstract class Resource[T: ClassTag] extends Directives {

  protected val resourceName: String

  protected def routes: Route = createResource ~ readResource ~ updateResource ~ deleteResource

  private def createResource: Route = {
    pathPrefix(resourceName) {
      pathEnd {
        post {
          entity(as[String]) { newResource =>
            complete(s"A new $resourceName has been created")
          }
        }
      }
    }
  }

  private def readResource: Route = {
    pathPrefix(resourceName) {
      path(Segment) { resourceId =>
        get {
          complete(s"Here is your $resourceName - $resourceId")
        }
      }
    }
  }

  private def updateResource: Route = {
    pathPrefix(resourceName) {
      path(Segment) { resourceId =>
        put {
          entity(as[String]) { updateResource =>
            complete(s"The $resourceName - $resourceId has been updated")
          }
        }
      }
    }
  }

  private def deleteResource: Route = {
    pathPrefix(resourceName) {
      path(Segment) { resourceId =>
        delete {
          complete(s"The $resourceName - $resourceId has been deleted")
        }
      }
    }
  }

}