package controllers

import play.api.i18n.Messages
import play.api.mvc._
import play.api.data.Form

import Models.Tasks._
import Models.Users.persistant.{User, UserRepositoryImpl}
import Models.Users.service.UsersServiceImpl
import Models.Users.UserForm._
import Models.PrivateExecutionContext._

import scala.concurrent.Future
import javax.inject._

@Singleton
class MainPageController @Inject()(userRepository: UserRepositoryImpl,
                                   usersService: UsersServiceImpl,
                                   cc: ControllerComponents)
  extends AbstractController(cc) {

  private object LoginUserForm {

    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибки валидации.
     *
     * @param formWithErrors Форма с ошибками валидации.
     * @return Результат BadRequest с формой, содержащей ошибки.
     */
    def FormWithError(formWithErrors: Form[User])(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.users.registerUser(formWithErrors))
      }

    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибку дублирования логина.
     *
     * @return Результат BadRequest с формой, содержащей ошибку дублирования логина.
     */
    def FormWithDuplicateLoginError(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.users.registerUser(userForm.withError("login", "Этот логин уже занят")))
      }

    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибку отсутствия пользователя с таким логином.
     *
     * @return Результат BadRequest с формой, содержащей ошибку отсутствия пользователя с таким логином.
     */
    def FormWithInvalidLogin(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.users.loginUser(userForm.withError("login", "Неправильный логин")))
      }

    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибку неверного пароля.
     *
     * @param userData Пользовательские данные для заполнения формы.
     * @return Результат BadRequest с формой, содержащей ошибку неверного пароля.
     */
    def FormWithInvalidPassword(userData: User)(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.users.loginUser(userForm.fill(userData).withError("password", "Неверный пароль")))
      }

    /**
     * Генерирует ответ InternalServerError с непредвиденной ошибкой.
     *
     * @param ex ошибка.
     * @return Результат InternalServerError с сообщением ошибки.
     */
    def UnexpectedError(ex: Throwable): Result =
      InternalServerError(s"Произошла ошибка: ${ex.getMessage}")
  }

  /**
   * Действие для отображения страницы регистрации пользователя.
   * GET /register
   *
   * @return HTTP-ответ с HTML-страницей для регистрации пользователя.
   */
  def showRegistrationForm: Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Accepted(views.html.users.registerUser(userForm))
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
  def saveUser: Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import LoginUserForm._

    userForm.bindFromRequest().fold(
      formWithErrors => FormWithError(formWithErrors),
      userData => {
        userRepository.checkUserByLogin(userData.login).flatMap { userExists =>
          if (userExists) {
            // Если пользователь с таким логином уже существует, добавляем ошибку к форме userForm
            FormWithDuplicateLoginError
          } else {
            // Регистрируем пользователя и перенаправляем на страницу входа
            usersService.createUser(userData.login, userData.password)
              .map { user =>
                userRepository.insertUser(user)
                Redirect(routes.MainPageController.firstTask).withSession("userLogin" -> userData.login)
              }
          }
        }
      }
    ).recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для отображения страницы входа пользователя.
   * GET /login
   *
   * @return HTTP-ответ с HTML-страницей для входа пользователя.
   */
  def showLoginForm: Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Accepted(views.html.users.loginUser(userForm))
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
  def login: Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import LoginUserForm._

    userForm.bindFromRequest().fold(
      formWithErrors => FormWithError(formWithErrors),
      userData => {
        userRepository.checkUserByLogin(userData.login).flatMap { userExist =>
          if (userExist) {
            usersService.checkUserPassword(userData.login, userData.password).flatMap { correctPassword =>
              if (correctPassword) {
                // Пароль верный, перенаправляем на главную страницу
                Future.successful(Redirect(routes.ToDoListController.get_all).withSession("userLogin" -> userData.login))

              } else {
                // Пароль неверный, добавляем ошибку к форме userForm
                FormWithInvalidPassword(userData)
              }
            }
          } else {
            // Нет такого пользователя, добавляем ошибку к форме userForm
            FormWithInvalidLogin
          }
        }
      }
    ).recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для отображения страницы создания первого задания.
   * GET /index
   *
   * @return HTTP-ответ с HTML-страницей для создания первого задания.
   */
  def firstTask: Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Accepted(views.html.users.index(TaskForm.taskForm))
  }

  /**
   * Действие для отображения главной страницы.
   * GET /
   *
   * @return HTTP-ответ с HTML-страницей для авторизации или регистрации.
   */
  def weclomePage: Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    Accepted(views.html.welcomePage("Filler. Need to fix"))
  }

  /**
   * Действие для отображения главной страницы после выхода пользователя.
   * GET /logout
   *
   * @return HTTP-ответ с HTML-страницей для авторизации или регистрации.
   */
  def logout: Action[AnyContent] = Action {
    Redirect(routes.MainPageController.weclomePage).withSession()
  }
}
