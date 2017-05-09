package io.meccrm.framework

class ApplicationLauncher[T <: Bootable: Manifest] extends App {
  val manifest = implicitly[Manifest[T]]
  val bootable = manifest.runtimeClass.newInstance.asInstanceOf[T]
  sys.ShutdownHookThread(bootable.halt())
  bootable.boot()
}