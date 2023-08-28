package Models.Users.service

import Models.Users.errors.DuplicatedAccountException
import Models.Users.persistant.{User, UserRepositoryImpl}
import Models.PrivateExecutionContext._

import javax.inject.Inject
import scala.concurrent.Future
import org.mindrot.jbcrypt.BCrypt

trait UsersService {
  def createUser(login: String, password: String): Future[User]

  def checkUserPassword(login: String, password: String): Future[Boolean]

}

class UsersServiceImpl @Inject()(userRepository: UserRepositoryImpl) extends UsersService {

  /**
   * Метод для создания нового пользователя с указанным логином и паролем.
   *
   * @param login    Логин нового пользователя.
   * @param password Пароль нового пользователя.
   * @return Future с созданным пользователем, если логин уникален.
   *         В случае, если пользователь с таким логином уже существует, Future завершится ошибкой DuplicatedAccountException.
   */
  override def createUser(login: String, password: String): Future[User] =
    userRepository.checkUserByLogin(login).map {
      case false => Future.successful(User(login, hashingPassword(password)))
      case _ => Future.failed(new DuplicatedAccountException)
    }.flatten

  /**
   * Метод для проверки соответствия пароля указанному логину.
   *
   * @param login    Логин пользователя для проверки.
   * @param password Пароль для проверки.
   * @return Future с булевым значением: true, если пароль соответствует указанному логину, и false в противном случае.
   */
  override def checkUserPassword(login: String, password: String): Future[Boolean] =
    userRepository.getUserPassword(login).map { hashedPassword =>
      BCrypt.checkpw(password, hashedPassword)
    }

  /**
   * Метод для хеширования пароля пользователя.
   *
   * @param password Пароль пользователя для хеширования.
   * @return Захешированный пароль.
   */
  private def hashingPassword(password: String): String = {
    lazy val hashingRounds = 10
    BCrypt.hashpw(password, BCrypt.gensalt(hashingRounds))
  }

}
