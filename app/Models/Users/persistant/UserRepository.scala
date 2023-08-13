package Models.Users.persistant

import Models.Connection.Connection

import Models.PrivateExecutionContext._
import javax.inject.Inject
import scala.concurrent.Future
import scala.util.{Failure, Success}

trait UserRepository {
  def insertUser(user: User): Unit

  def checkUserByLogin(login: String): Future[Boolean]

  def getUserPassword(login: String): Future[String]

  def getUsers: Future[Seq[User]]
}

class UserRepositoryImpl @Inject()(userModelDao: UserModelDaoImpl) extends UserRepository {

  /**
   * Метод для получения списка всех пользователей из базы данных.
   *
   * @return Future с списком пользователей.
   */
  override def getUsers: Future[Seq[User]] =
    Connection.db.run(userModelDao.getUsers).andThen {
      case Success(users) => println(s"The following users were retrieved from the database: $users.")
      case Failure(ex) => println(ex)
    }

  /**
   * Мето для вставки нового пользователя в базу данных.
   *
   * @param user Пользователь для вставки.
   */
  override def insertUser(user: User): Unit =
    Connection.db.run(userModelDao.insertUser(user)).onComplete {
      case Success(_) => println(s"A new user has been added! Login: ${user.login}")
      case Failure(ex) => println(s"Failed to add a user: ${ex.getMessage}")
    }

  /**
   * Метод для проверки уникальности логина пользователя в базе данных.
   *
   * @param login Логин пользователя для проверки.
   * @return Future с булевым значением: true, если пользователь существует, и false, если нет.
   */
  override def checkUserByLogin(login: String): Future[Boolean] =
    Connection.db.run(userModelDao.checkUserByLogin(login))

  /**
   * Метод для получения пароля пользователя по указанному логину.
   *
   * @param login Логин пользователя.
   * @return Future со строкой, представляющей пароль пользователя.
   */
  override def getUserPassword(login: String): Future[String] =
    Connection.db.run(userModelDao.getUserPassword(login))

}
