package controllers


import play.api.mvc._
import Models.Users.persistant.{User, UserJSONImpl, UserRepositoryImpl}
import Models.Users.service.UsersServiceImpl
import Models.PrivateExecutionContext._
import play.api.libs.json._

import scala.concurrent.Future
import javax.inject._

@Singleton
class MainPageController @Inject()(userRepository: UserRepositoryImpl,
                                   usersService: UsersServiceImpl,
                                   userJSON: UserJSONImpl,
                                   cc: ControllerComponents) extends AbstractController(cc) {

  implicit val format: OFormat[User] = Json.format[User]


  private object LoginUserForm {



    def FormWithDuplicateLoginError: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("login" -> "This login is already exist"))
      }


    def FormWithUnexpectedError(ex: Throwable): Result =
      BadRequest(Json.obj("error" -> s"Unforeseen error: ${ex.getMessage}."))


    def FormWithInvalidPassword: Result =
      BadRequest(Json.obj("error" -> "Incorrect password"))

  }


  def saveUser: Action[JsValue] = Action.async(parse.json) { implicit request =>
    import LoginUserForm._

    val userJson = request.body
    userJSON.checkUserByLogin(userJson).flatMap { userExists =>
      if (userExists) {
        FormWithDuplicateLoginError
      } else {
        userJSON.createUser(userJson) match {
          case Left(newUser) => {
            userRepository.insertUser(newUser)
            Future.successful(Created(Json.obj("success" -> "New user has been registered")))
          }.recover {
            case ex: Throwable =>
              FormWithUnexpectedError(ex)
          }
          case Right(value) => Future.successful(BadRequest(value))
        }
      }
    }
  }



  def login: Action[JsValue] = Action.async(parse.json) { implicit request =>
    import LoginUserForm._

    val userJson = request.body

    userJson.validate[User] match {
      case JsSuccess(user, _) =>
        usersService.checkUserPassword(user.login, user.password).map { correctPassword =>
          if (correctPassword) {
            Redirect(routes.ToDoListController.getTasks).withCookies(Cookie("userLogin", user.login))
          } else {
            FormWithInvalidPassword
          }
        }.recover {
          case ex: Throwable =>
           BadRequest(Json.obj("error" -> s"An error occurred: ${ex.getMessage}."))
        }
      case JsError(_) =>
        Future.successful(BadRequest(Json.obj("error" -> "Invalid User JSON")))
    }
  }


  def debugUsers: Action[AnyContent] = ???

}
