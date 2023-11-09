package Models.Users.persistant

import io.swagger.annotations.ApiModel

@ApiModel
final case class User(
                       login: String,
                       password: String
                     )

object SlickTablesUser {

  import slick.jdbc.PostgresProfile.api._

  class TableUser(tag: Tag) extends Table[User](tag, Some("tasks"), "Users") {

    def login = column[String]("login", O.PrimaryKey)

    def password = column[String]("password")

    override def * = (login, password) <> (User.tupled, User.unapply)
  }

  lazy val userTable = TableQuery[TableUser]

}
