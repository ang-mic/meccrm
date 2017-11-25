package io.meccrm.util

import io.meccrm.application.db.MeccrmDatabase
import org.scalatest.{AsyncFlatSpec, ParallelTestExecution, Suite, BeforeAndAfterEach}

import scala.concurrent.Future

object H2TestUtil {

  import io.meccrm.framework.db.slick.{Connection, Database}

  /**
    * Provides a single H2 instance for the whole test suite. The db tables are created
    * and dropped for each test case. It can be safely used to run test suites in parallel.
    */
  abstract class AsyncH2Spec extends AsyncFlatSpec with  BeforeAndAfterEach {
    import scala.concurrent.Await
    import scala.concurrent.duration._

    protected val db = new MeccrmDatabase(new H2Connection(suiteName = this.suiteName))

    /** Executed before each test case, it blocks to guaranty data integrity during testing */
    override def beforeEach() = {
      Await.ready(db.createTables, 3 seconds)
      //super.beforeEach() // To be stackable, must call super.beforeEach
    }

    /** Executed after each test case, it blocks to guaranty data integrity during testing */
    override def afterEach() = {
      Await.ready(db.dropTables, 3 seconds)
      //try super.afterEach() // To be stackable, must call super.afterEach
      //finally Await.ready(db.dropTables, 3 seconds)
    }
  }

  /**
    * Provides a single H2 instance for per test case. The db tables are created
    * and dropped for each test case. It can be safely used to run test cases in parallel.
    */
  abstract class ParallelH2Spec extends AsyncFlatSpec {
    this: ParallelTestExecution =>

    /**
      * It provides a database instance to its enclosing scope. Use curring to leverage the database.
      * It is really important to provide a unique `testId` for each test case which will potentially run
      * in parallel with other test using a database instance.
      *
      * {{{
      *  withDb("uniqueId") { db => ... }
      * }}}
      *
      * @param testId unique ID of the test case. It used for the name of the in memory db (use letters only)
      * @param block a function which uses [[io.meccrm.application.db.MeccrmDatabase MeccrmDatabase]] to produce a Future
      * @tparam A generic type representing [[org.scalatest.compatible.Assertion Assertion]] (but not necessarily)
      */
    //TODO: Maybe auto-generate the testId
    protected def withDb[A](testId: String)(block: MeccrmDatabase => Future[A]): Future[A] = for {
      db     <- Future.successful(new MeccrmDatabase(new H2Connection(this.suiteName, testId)))
      _      <- db.createTables
      result <- block(db)
      _      <- db.dropTables
    } yield result
  }

  private final class H2Connection(suiteName: String, testId: String = "") extends Connection {

    import slick.jdbc.H2Profile.api.Database

    override val db = Database.forURL(s"jdbc:h2:mem:${suiteName + testId};DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=5", driver = "org.h2.Driver")

    override val profile = slick.jdbc.H2Profile
  }

  private implicit final class DatabaseExtended(db: Database) {
    import db.schema.profile.api._

    def createTables = db.runAsync(db.ddl.create)

    def dropTables = db.runAsync(db.ddl.drop.asTry)
  }
}


