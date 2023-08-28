package Models.Users.persistant

import SlickTablesUser.userTable
import slick.dbio.Effect
import slick.jdbc.PostgresProfile.api._
import slick.sql.{FixedSqlAction, SqlAction}

import javax.inject.Inject

trait UserModelDao {
  def insertUser(user: User): FixedSqlAction[Int, NoStream, Effect.Write]

  def checkUserByLogin(login: String): FixedSqlAction[Boolean, NoStream, Effect.Read]

  def getUserPassword(login: String): SqlAction[String, NoStream, Effect.Read]

  def getUsers: FixedSqlAction[Seq[User], NoStream, Effect.Read]
}

class UserModelDaoImpl @Inject() extends UserModelDao {

  /**
   * Метод вставляет нового пользователя в базу данных.
   *
   * @param user Пользователь для вставки.
   * @return Запрос к базе данных для вставки пользователя.
   */
  override def insertUser(user: User): FixedSqlAction[Int, NoStream, Effect.Write] =
    userTable += user

  /**
   * Метод проверяет наличие пользователя с указанным логином в базе данных.
   *
   * @param login Логин пользователя для проверки.
   * @return Запрос к базе данных для проверки наличия пользователя с указанным логином.
   */
  override def checkUserByLogin(login: String): FixedSqlAction[Boolean, NoStream, Effect.Read] =
    userTable
      .filter(_.login === login)
      .exists
      .result

  /**
   * Метод получает пароль пользователя по указанному логину.
   *
   * @param login Логин пользователя.
   * @return Запрос к базе данных для получения пароля пользователя.
   */
  override def getUserPassword(login: String): SqlAction[String, NoStream, Effect.Read] =
    userTable
      .filter(user => user.login === login)
      .map(_.password)
      .result
      .head

  /**
   * Метод получает список всех пользователей из базы данных.
   *
   * @return Запрос к базе данных для получения списка пользователей.
   */
  override def getUsers: FixedSqlAction[Seq[User], NoStream, Effect.Read] =
    userTable.result
}
