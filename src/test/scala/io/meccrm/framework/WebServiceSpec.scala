package io.meccrm.framework

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}


//TODO: Create a general trait which include all the test traits
class WebServiceSpec extends WordSpec with Matchers with ScalatestRouteTest {
  "The endpoint X" should {
    "return `hello world` for GET requests to the root path" in {
      Get("/hello") ~> Router.route ~> check {
        responseAs[String] shouldEqual "<h1>Say hello to akka-http</h1>"
      }
    }
  }
}

object Router {
  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }
}
