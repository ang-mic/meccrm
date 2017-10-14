  // Project related information
  organization := "io.meccrm"
  version := "0.0.1"
  name := "meccrm"

  // Scala related information
  ivyScala := ivyScala.value map(_.copy(overrideScalaVersion = true))
  scalaVersion := "2.12.3"
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

  //Packaging and execution instructions
  enablePlugins(JavaAppPackaging)                             // Enables packaging and specifies the type of the package
  mainClass in Universal := Some("com.example.package.Class") // Specifies the class with the 'main' methods
  executableScriptName := "start"

  // External dependencies of the project
  libraryDependencies ++= {
    // val akkaVersion = "2.4.9"
    Seq(
      "org.scalatest"     %% "scalatest"         % "3.0.3" % "test",
      "com.typesafe"      % "config"             % "1.3.1",
      "com.typesafe.akka" %% "akka-http"         % "10.0.6",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.6",
      "com.h2database"    % "h2"                 % "1.4.191",
      "org.postgresql"    % "postgresql"         % "9.4-1200-jdbc4",
      "org.flywaydb"      % "flyway-core"        % "4.2.0"
    )
  }

  flywayUrl := "jdbc:postgresql://localhost:5432/crm"
  flywayUser := "meccrm"