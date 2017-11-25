package io.meccrm.framework.db

import scala.concurrent.{Future, ExecutionContext}

/**
  * Encapsulate the core operations for managing the RDBMS with
  * with database migrations.
  */
trait MigrationManager {

  /** Upgrades the DB to the latest migration version */
  def upgrade(implicit ex: ExecutionContext): Future[Unit]

  /** Destroys and re-constructs the DB to the latest version */
  def rebuild(implicit ex: ExecutionContext): Future[Unit]

  def validate(implicit ex: ExecutionContext): Future[Unit]

  def info: String
}
