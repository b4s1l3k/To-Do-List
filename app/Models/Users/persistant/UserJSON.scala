package Models.Users.persistant

import Models.Users.service.UsersServiceImpl
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

class UserJSONImpl @Inject()(userRepository: UserRepositoryImpl, usersService: UsersServiceImpl) extends UserJSON {

  implicit val format: OFormat[User] = Json.format[User]

  override def createUser(userJSON: JsValue): Either[User, JsObject]  =
    userJSON.validate[User] match {
      case JsSuccess(user, _) =>
        Left(User(user.login, usersService.hashingPassword(user.password)))
      case JsError(_) => Right(Json.obj("error" -> "Invalid User JSON"))
    }

  override def insertUser(user: User): Future[JsValue] =
        userRepository.insertUser(user).map { _ =>
          Json.obj("success" -> s"A new user has been added! Login: ${user.login}")
        }

  override def checkUserByLogin(userJSON: JsValue): Future[Boolean] =
    userJSON.validate[User] match {
      case JsSuccess(user, _) => userRepository.checkUserByLogin(user.login)
      case JsError(_) => Future.successful(false)
    }

}
