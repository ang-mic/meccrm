package io.meccrm.framework.fixture

import java.net.ServerSocket

import io.meccrm.application.MeccrmBootable
import io.meccrm.framework.environment.TestEnv

trait AppFixture {

  /**
    * Runs an instance of MeccrmBootable with TestEnv
    * TODO: try - finally block
    */
  def withApp[A](block: => A) = {
    val app = new MeccrmBootable with TestEnv
    app.boot()
    block
    app.halt()
  }

  //FIXME: Too naive implementation, improve it
  def getAvailablePort: Int = {
      val socket = new ServerSocket(0)
      val port   = socket.getLocalPort
      socket.setReuseAddress(true)
      socket.close()
      port
  }


}
