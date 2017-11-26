import sbt._

object Dependencies {

  private val akkaV      = "2.5.6"
  private val akkaHttpV  = "10.0.10"
  private val slickV     = "3.2.1"

  lazy val service = http ++ db ++ utils ++ test

  val http = Seq(
      "com.typesafe.akka"   %% "akka-http"          % akkaHttpV
  )

  val db = Seq(
    "org.postgresql"        %  "postgresql"         % "42.1.4",
    "com.typesafe.slick"    %% "slick"              % slickV,
    "com.typesafe.slick"    %% "slick-hikaricp"     % slickV,
    "org.flywaydb"          %  "flyway-core"        % "4.2.0"
  )

  val utils = Seq(
    "com.typesafe"               %  "config"         % "1.3.2",
    "ch.qos.logback"             % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging"  % "3.7.2"
  )

  val test = Seq(
    "com.h2database"        %  "h2"                % "1.4.196",
    "org.scalatest"         %% "scalatest"         % "3.0.4",
    "com.typesafe.akka"     %% "akka-http-testkit" % "10.0.10"
  ).map(_ % Test)
}
