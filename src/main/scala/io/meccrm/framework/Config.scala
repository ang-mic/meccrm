package io.meccrm.framework

/**
  * It is the interface for all the configuration need for this app.
  * Also it acts as wrapper for the TypeSafe config library
  */
trait Config {

  //TODO: find examples of configuration
  //FIXME: This method should return Config
  def load(): Unit
}
