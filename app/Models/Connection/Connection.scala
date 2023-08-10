package Models.Connection

import slick.jdbc.PostgresProfile.api._

object Connection {
  lazy val dbType = "postgres"
  val db = Database.forConfig(dbType)
}
