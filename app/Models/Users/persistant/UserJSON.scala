package Models.Users.persistant

import play.api.libs.json._

import javax.inject.Inject
import scala.concurrent.Future
import Models.PrivateExecutionContext._
import org.mindrot.jbcrypt.BCrypt

trait UserJSON {

  def insertUser(user: User): Future[JsValue]

  def checkUserByLogin(userJSON: JsValue): Future[Boolean]

  def createUser(userJSON: JsValue): Either[User, JsObject]

}

class UserJSONImpl @Inject()(userRepository: UserRepositoryImpl) extends UserJSON {

  implicit val format: OFormat[User] = Json.format[User]

  /**
   * Метод для создания пользователя на основе JSON-данных.
   *
   * @param userJSON JSON-данные пользователя для создания.
   * @return Either[User, JsObject], где Left(User) - созданный пользователь,
   *         Right(Json) - ошибка.
   */
  override def createUser(userJSON: JsValue): Either[User, JsObject]  =
    userJSON.validate[User] match {
      case JsSuccess(user, _) =>
        Left(User(user.login, hashingPassword(user.password)))
      case JsError(_) => Right(Json.obj("error" -> "Invalid User JSON"))
    }

  /**
   * Метод для вставки пользователя в базу данных.
   *
   * @param user Пользователь для вставки.
   * @return Future[JsValue] с информацией об успешной вставке.
   */
  override def insertUser(user: User): Future[JsValue] =
        userRepository.insertUser(user).map { _ =>
          Json.obj("success" -> s"A new user has been added! Login: ${user.login}")
        }

  /**
   * Метод для проверки уникальности логина.
   *
   * @param userJSON JSON-данные пользователя для проверки.
   * @return Future[Boolean], где true - пользователь существует,
   *         false - пользователь не существует.
   */
  override def checkUserByLogin(userJSON: JsValue): Future[Boolean] =
    userJSON.validate[User] match {
      case JsSuccess(user, _) => userRepository.checkUserByLogin(user.login)
      case JsError(_) => Future.successful(false)
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
