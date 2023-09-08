package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n.Messages
import Models.Tasks.TaskForm._
import Models.Tasks.persistant._
import Models.PrivateExecutionContext._
import Models.Tasks.service.TaskServiceImpl
import play.api.data.Form

import java.time.LocalDate
import scala.concurrent.Future

@Singleton
class ToDoListController @Inject()(taskRepository: TaskRepositoryImpl, taskService: TaskServiceImpl, cc: ControllerComponents)
  extends AbstractController(cc) {

  /**
   * Объект для хранения констант с названиями форм.
   */
  private object FormNames {
    val EditTask = "editTask"
    val CreateTask = "createTask"
  }

  private object TasksForm {

    import FormNames._

    /**
     * Генерирует ответ NotFound с сообщением о том, что задача с указанным идентификатором не найдена.
     *
     * @return Результат Future[Result], содержащий NotFound с сообщением.
     */
    def NoSuchElementExceptionError(implicit messages: Messages): Result =
      NotFound(views.html.tasks.notFound("Задача с указанным идентификатором не найдена"))


    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибки валидации.
     *
     * @param formWithErrors Форма с ошибками валидации.
     * @return Результат BadRequest с формой, содержащей ошибки.
     */
    def SaveFormWithErrors(formWithErrors: Form[Task])(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.tasks.createTask(formWithErrors))
      }

    /**
     * Генерирует ответ BadRequest с формой редактирования задачи, содержащей ошибки валидации.
     *
     * @param formWithErrors Форма с ошибками валидации.
     * @return Результат BadRequest с формой редактирования задачи, содержащей ошибки.
     */
    def UpdateFormWithErrors(formWithErrors: Form[Task])(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        BadRequest(views.html.tasks.editTask(formWithErrors))
      }

    /**
     * Генерирует ответ BadRequest с формой, содержащей ошибку указания дедлайна ранее текущей даты.
     *
     * @param formName Название формы, указывающее, какую форму следует отобразить (CreateTask или EditTask).
     * @param formData Данные задачи для заполнения формы.
     * @return Результат BadRequest с формой, содержащей ошибку указания дедлайна ранее текущей даты.
     */
    def FormWithWrongDate(formName: String)(formData: Task)(implicit request: Request[AnyContent], messages: Messages): Future[Result] =
      Future.successful {
        formName match {
          case CreateTask =>
            BadRequest(views.html.tasks.createTask(taskForm
              .fill(formData)
              .withError("Дедлайн", "Дедлайн раньше текущей даты"))
            )
          case EditTask =>
            BadRequest(views.html.tasks.editTask(editForm
              .fill(formData)
              .withError("Дедлайн", "Дедлайн раньше текущей даты"))
            )
        }
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
   * Обертка для выполнения действий с идентификатором пользователя из сессии.
   *
   * @param f       Функция, принимающая идентификатор пользователя и возвращающая Future с результатом.
   * @param request HTTP-запрос.
   * @return Future с результатом выполнения функции или редиректом на страницу авторизации.
   */
  private def withUserLogin(f: String => Future[Result])(implicit request: Request[AnyContent]): Future[Result] = {
    request.session.get("userLogin") match {
      case Some(userLogin) => f(userLogin)
      case None => Future.successful(Redirect(routes.MainPageController.login))
    }
  }

  /**
   * Действие для отображения всех задач.
   * GET /tasks
   *
   * @return HTTP-ответ с HTML-страницей, содержащей список всех задач.
   */
  def get_all: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskRepository.getTasks(userLogin).map { tasks =>
        Ok(views.html.tasks.listOfTasks(tasks))
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }


  /**
   * Действие для отображения информации о задаче по указанному идентификатору.
   * GET /tasks/:id
   *
   * @param id Идентификатор задачи.
   * @return HTTP-ответ с HTML-страницей, содержащей информацию о задаче.
   *         Если задача с указанным идентификатором не найдена, возвращает страницу с сообщением о не найденной задаче.
   */
  def getTask(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import TasksForm._

    withUserLogin { userLogin =>
      taskRepository.getOneTask(id, userLogin).map { task =>
        Ok(views.html.tasks.taskDetails(task))
      }.recover {
        case _: NoSuchElementException =>
          NoSuchElementExceptionError
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для отображения страницы создания новой задачи.
   * GET /tasks/create
   *
   * @return HTTP-ответ с HTML-страницей для создания новой задачи.
   */
  def create: Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    Accepted(views.html.tasks.createTask(taskForm))
  }

  /**
   * Обработчик POST-запроса на создание новой задачи.
   * POST /tasks/create
   *
   * @return HTTP-ответ с результатом создания новой задачи.
   */
  def save: Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import TasksForm._
    import FormNames._

    withUserLogin { userLogin =>
      taskForm.bindFromRequest().fold(
        formWithErrors => SaveFormWithErrors(formWithErrors),
        formData => {
          if (formData.dueDate.isBefore(LocalDate.now())) {
            FormWithWrongDate(CreateTask)(formData)
          } else {
            taskService.createTask(userLogin,
                formData.title,
                formData.description,
                formData.dueDate,
                formData.supplement,
                status = false)
              .map { task =>
                taskRepository.insertTask(task)
                Redirect(routes.ToDoListController.get_all).withSession("userLogin" -> userLogin)
              }
          }
        }
      )
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для отображения страницы редактирования задачи по указанному идентификатору.
   * GET /tasks/edit/:id
   *
   * @param id Идентификатор задачи.
   * @return HTTP-ответ с HTML-страницей для редактирования задачи.
   *         Если задача с указанным идентификатором не найдена, возвращает страницу с сообщением о не найденной задаче.
   */
  def edit(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import TasksForm._

    withUserLogin { userLogin =>
      taskRepository.getOneTask(id, userLogin).map { task =>
        Accepted(views.html.tasks.editTask(editForm.fill(task)))
      }
    }.recover {
      case _: NoSuchElementException =>
        NoSuchElementExceptionError
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Обработчик POST-запроса на обновление задачи.
   * POST /tasks/edit
   *
   * @return HTTP-ответ с результатом обновления задачи.
   */
  def update: Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import TasksForm._
    import FormNames._

    withUserLogin { userLogin =>
      editForm.bindFromRequest().fold(
        formWithErrors => UpdateFormWithErrors(formWithErrors),
        formData => {
          if (formData.dueDate.isBefore(LocalDate.now())) {
            FormWithWrongDate(EditTask)(formData)
          } else {
            val updatedTask = Task(login = userLogin,
              formData.id,
              formData.title,
              formData.description,
              formData.dueDate,
              formData.supplement,
              status = false)
            taskService.updateTask(updatedTask, userLogin).map { _ =>
              Redirect(routes.ToDoListController.get_all).withSession("userLogin" -> userLogin)
            }
          }
        }
      )
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для удаления задачи по указанному идентификатору.
   * GET /tasks/delete/:id
   *
   * @param id Идентификатор задачи для удаления.
   * @return HTTP-ответ с результатом удаления задачи.
   */
  def deleteOneTask(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    import TasksForm._

    withUserLogin { userLogin =>
      for {
        doneTask <- taskRepository.getOneTask(id, userLogin)
        _ <- Future.successful(taskRepository.deleteTask(doneTask))
        remainingTasks <- taskRepository.getTasks(userLogin)
      } yield Ok(views.html.tasks.deleteResult(doneTask, remainingTasks))
    }.recover {
      case _: NoSuchElementException =>
        NoSuchElementExceptionError
      case ex =>
        UnexpectedError(ex)
    }
  }


  /**
   * Действие для отображения списка удаленных задач.
   * GET /deleted
   *
   * @return HTTP-ответ с HTML-страницей, содержащей список удаленных задач.
   */
  def doneTasks: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskRepository.getDoneTasks(userLogin).map { doneTasks =>
        Ok(views.html.tasks.doneTasks(doneTasks))
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  /**
   * Действие для очистки указанной таблицы в базе данных.
   * GET /clear/:tableName
   *
   * @param tableName Имя таблицы, которую необходимо очистить.
   * @return HTTP-ответ с редиректом на страницу со списком всех задач.
   */
  def clear(tableName: String): Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskRepository.deleteTasks(tableName, userLogin).map { _ =>
        Redirect(routes.ToDoListController.get_all).withSession("userLogin" -> userLogin)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }
}
