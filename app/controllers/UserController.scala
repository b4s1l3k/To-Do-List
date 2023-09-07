package controllers

import play.api.mvc._
import Models.Users.persistant.{User, UserJSONImpl, UserRepositoryImpl}
import Models.Users.service.UsersServiceImpl
import Models.PrivateExecutionContext._
import io.swagger.annotations.{Api, ApiImplicitParam, ApiImplicitParams, ApiOperation, ApiResponse, ApiResponses}
import play.api.libs.json._

import scala.concurrent.Future
import javax.inject._

@Api(value = "User Controller")
@Singleton
class UserController @Inject()(userRepository: UserRepositoryImpl, usersService: UsersServiceImpl,
                                   userJSON: UserJSONImpl, cc: ControllerComponents) extends AbstractController(cc) {

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

    def InvalidUserJson: Future[Result] =
      Future.successful {
        ExpectationFailed(Json.obj("error" -> "Invalid User JSON"))
      }
  }


  @ApiOperation(
    value = "Create a new user",
    notes = "Creating a new user with a unique login."
  )
  @ApiResponses(Array(
    new ApiResponse(code = 201, message = "A new task has been added! Title: \"task title\"."),
    new ApiResponse(code = 400, message = "This login is already exist."),
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      name = "User data",
      value = "User data in JSON format",
      required = true,
      dataTypeClass = classOf[User],
      paramType = "body")
  ))
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
          }
          case Right(value) => Future.successful(BadRequest(value))
        }
      }
    }.recover {
      case ex: Throwable =>
        FormWithUnexpectedError(ex)
    }
  }

  @ApiOperation(
    value = "User Authorization",
    notes = "User authorization by login and password."
  )
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Incorrect password."),
    new ApiResponse(code = 417, message = "Invalid User JSON."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      name = "User data",
      value = "User data in JSON format",
      required = true,
      dataTypeClass = classOf[User],
      paramType = "body")
  ))
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
            FormWithUnexpectedError(ex)
        }
      case JsError(_) =>
        InvalidUserJson
    }
  }

}
