  addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.0-RC2")
  addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")
  addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.2.0")
  resolvers += "Flyway" at "https://flywaydb.org/repo"
