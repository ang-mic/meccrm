package io.meccrm.framework.environment

import io.meccrm.application.http.WebInterface.WebInterface

trait WebService {
  protected val webInterface: WebInterface
}

