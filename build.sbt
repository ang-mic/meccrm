  // Project related information
  organization := "io.meccrm"
  version := "0.0.1"
  name := "meccrm"

  // Scala related information
  ivyScala := ivyScala.value map(_.copy(overrideScalaVersion = true))
  scalaVersion := "2.12.4"
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

  //Packaging and execution instructions
  enablePlugins(JavaAppPackaging)                             // Enables packaging and specifies the type of the package
  mainClass in Universal := Some("com.example.package.Class") // Specifies the class with the 'main' methods
  executableScriptName := "start"

  libraryDependencies ++= Dependencies.service
  parallelExecution in Test := true