package controllers

import play.api.i18n.Messages
import play.api.mvc._
import javax.inject._

import Models.Tasks._
import Models.Users.UserForm._
import Models.PrivateExecutionContext._
import Models.Users.UsersMethods

import scala.concurrent.Future

@Singleton
class MainPageController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  /**
   * Действие для отображения страницы регистрации пользователя.
   * GET /register
   *
   * @return HTTP-ответ с HTML-страницей для регистрации пользователя.
   */
  def showRegistrationForm(): Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Ok(views.html.users.registerUser(userForm))
  }

  /**
   * Обработчик POST-запроса на регистрацию пользователя.
   * POST /register
   *
   * @return HTTP-ответ с результатом регистрации пользователя:
   *         - Если форма заполнена с ошибками, возвращает страницу регистрации с отображением ошибок.
   *         - Если регистрация прошла успешно, регистрирует пользователя в базе данных,
   *           перенаправляет на страницу создания перовго задания.
   *         - Если произошла непредвиденная ошибка, возвращает страницу регистрации с общей ошибкой.
   */
  def saveUser(): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    userForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.users.registerUser(formWithErrors))),
      userData => {
        UsersMethods.checkUserByLogin(userData.login).flatMap { userExists =>
          if (userExists) {
            // Если пользователь с таким логином уже существует, добавляем ошибку к форме userForm
            val formWithDuplicateLoginError = userForm.withError("Логин", "Этот логин уже занят")
            Future.successful(BadRequest(views.html.users.registerUser(formWithDuplicateLoginError)))
          } else {
            // Регистрируем пользователя и перенаправляем на страницу входа
            val hashedPassword = UsersMethods.hashingPassword(userData.password)
            UsersMethods.createUser(userData.login, hashedPassword)
              .map { user =>
                UsersMethods.insertUser(user)
                Redirect(routes.MainPageController.firstTask()).withSession("userLogin" -> userData.login)
              }.recover {
                case _: Throwable =>
                  val errorForm = userForm.withGlobalError("Непредвиденная ошибка")
                  BadRequest(views.html.users.registerUser(errorForm))
              }
          }
        }
      }
    )
  }

  /**
   * Действие для отображения страницы входа пользователя.
   * GET /login
   *
   * @return HTTP-ответ с HTML-страницей для входа пользователя.
   */
  def showLoginForm(): Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Ok(views.html.users.loginUser(userForm))
  }

  /**
   * Обработчик POST-запроса на вход пользователя.
   * POST /login
   *
   * @return HTTP-ответ с результатом входа пользователя:
   *         - Если форма заполнена с ошибками, возвращает страницу входа с отображением ошибок.
   *         - Если вход выполнен успешно (логин и пароль совпадают), перенаправляет на страницу со всеми задачами пользователя.
   *         - Если пароль неверный, возвращает страницу входа с ошибкой валидации.
   */
  def login(): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    userForm.bindFromRequest().fold(
      formWithErrors => Future.successful(BadRequest(views.html.users.loginUser(formWithErrors))),
      userData => {
        UsersMethods.checkUserPassword(userData.login, userData.password).flatMap { correctPassword =>
          if (correctPassword) {
            // Пароль верный, перенаправляем на главную страницу
              Future.successful(Redirect(routes.ToDoListController.get_all()).withSession("userLogin" -> userData.login))

          } else {
            // Пароль неверный, добавляем ошибку к форме userForm
            val formWithWrongPasswordError = userForm
              .fill(userData)
              .withError("Пароль", "Неверный пароль")
            Future.successful(BadRequest(views.html.users.loginUser(formWithWrongPasswordError)))
          }
        }
      }
    )
  }


  /**
   * Действие для отображения страницы создания первого задания.
   * GET /index
   *
   * @return HTTP-ответ с HTML-страницей для создания первого задания.
   */
  def firstTask(): Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Ok(views.html.users.index(TaskForm.taskForm))
  }

  /**
   * Действие для отображения главной страницы.
   * GET /
   *
   * @return HTTP-ответ с HTML-страницей для авторизации или регистрации.
   */
  def weclomePage(): Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Ok(views.html.welcomePage("Filler. Need to fix"))
  }

  /**
   * Действие для отображения главной страницы после выхода пользователя.
   * GET /logout
   *
   * @return HTTP-ответ с HTML-страницей для авторизации или регистрации.
   */
  def logout(): Action[AnyContent] = Action {

    Redirect(routes.MainPageController.weclomePage()).withSession()
  }

  // Действие для отображения страницы с отладочной информацией о пользователях (не реализовано)
  def debugUsers: Action[AnyContent] = ???

}
