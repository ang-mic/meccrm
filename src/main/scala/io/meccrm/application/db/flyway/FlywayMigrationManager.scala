package io.meccrm.application.db.flyway

import org.flywaydb.core.api.MigrationVersion
import com.typesafe.config.Config
import io.meccrm.framework.db.MigrationManager

import scala.concurrent.Future
import org.flywaydb.core.Flyway

case class FlywayConfig(url: String, user: String, password: String, baseline: Option[MigrationVersion])

object FlywayConfig {
  def apply(typesafeConfig: Config): FlywayConfig = new FlywayConfig(
    url      = typesafeConfig.getString("url"),
    user     = typesafeConfig.getString("user"),
    password = typesafeConfig.getString("password"),
    baseline = None /*typesafeConfig.getStringOption("flyway.baselineVersion").map(MigrationVersion.fromVersion)*/)
}

class FlywayMigrationManager(flywayConfig: FlywayConfig) extends MigrationManager {

  import scala.concurrent.ExecutionContext

  private lazy val flyway: Flyway = FlywayMigrationManager.getFlyway(flywayConfig)

  def upgrade(implicit ex: ExecutionContext): Future[Unit] = Future(flyway.migrate())

  def rebuild(implicit ex: ExecutionContext): Future[Unit] = {
    flyway.clean
    upgrade
  }

  def validate(implicit ex: ExecutionContext): Future[Unit] = Future(flyway.validate())

  def info: String = ???
}

object FlywayMigrationManager {

  val upgrade   = "db-upgrade"
  val rebuild   = "db-rebuild"
  val validate  = "db-validate"
  val info      = "db-info"
  lazy val help =f"""
       |Database maintenance:
       |
       |  $upgrade%15s - Check database status and apply migrations when needed
       |  $info%15s - Display information about the database status
       |  $rebuild%15s - Destroys the database and re-builds from scratch
       |  $validate%15s - Validates the migration scripts
       |  """.stripMargin.trim

  def getFlyway(flywayConfig: FlywayConfig): Flyway = {
    val flyway = new Flyway
    flyway.setDataSource(flywayConfig.url, flywayConfig.user, flywayConfig.password)
    flyway
  }
}

