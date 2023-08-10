package Models.Connection

import slick.jdbc.PostgresProfile.api._

object Connection {
  private lazy val dbType = "postgres"
  val db = Database.forConfig(dbType)
}
