package Models.Users.persistant

final case class User(login: String, password: String)

object SlickTablesUser {

  import slick.jdbc.PostgresProfile.api._

  class TableUser(tag: Tag) extends Table[User](tag, Some("tasks"), "Users") {

    def login = column[String]("Логин", O.PrimaryKey)

    def password = column[String]("Пароль")

    override def * = (login, password) <> (User.tupled, User.unapply)
  }

  lazy val userTable = TableQuery[TableUser]

}
