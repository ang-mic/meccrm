package io.meccrm.framework

import scala.reflect.ClassTag

class ApplicationLauncher[T <: Bootable: ClassTag] extends App {
  val bootable = implicitly[ClassTag[T]].runtimeClass.newInstance.asInstanceOf[T]
  sys.ShutdownHookThread(bootable.halt())
  bootable.boot()
}