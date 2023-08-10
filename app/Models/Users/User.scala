package Models.Users

case class User(userId: Int, login: String, password: String)

object SlickTablesUser {

  import slick.jdbc.PostgresProfile.api._

  class TableUser(tag: Tag) extends Table[User](tag, Some("tasks"), "Users") {
    def userId = column[Int]("user_id")
    def login = column[String]("Логин", O.PrimaryKey)
    def password = column[String]("Пароль")

    override def * = (userId, login, password) <> (User.tupled, User.unapply)
  }

  lazy val userTable = TableQuery[TableUser]

}
