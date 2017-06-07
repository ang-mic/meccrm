package io.meccrm.framework.environment

import io.meccrm.application.config.ConfigComponent
import io.meccrm.application.http.ServerComponent
import io.meccrm.framework.fixture.AppFixture


trait TestEnv extends ServerComponent with ConfigComponent {

  override val config: AppConfig = new AppConfig with AppFixture {
    override val host = "test-host"
    override val port = getAvailablePort
  }

  override val server: HttpServer = new HttpServer
}