package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n.Messages
import Models.Tasks._
import Models.Tasks.TaskForm._
import Models.Tasks.Task
import Models.PrivateExecutionContext._

import scala.concurrent.Future

@Singleton
class ToDoListController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  /**
   * Обертка для выполнения действий с идентификатором пользователя из сессии.
   *
   * @param f        Функция, принимающая идентификатор пользователя и возвращающая Future с результатом.
   * @param request  HTTP-запрос.
   * @return         Future с результатом выполнения функции или редиректом на страницу авторизации.
   */
  private def withUserLogin(f: String => Future[Result])(implicit request: Request[AnyContent]): Future[Result] = {
    request.session.get("userLogin") match {
      case Some(userLogin) => f(userLogin)
      case None => Future.successful(Redirect(routes.MainPageController.login()))
    }
  }

  /**
   * Действие для отображения всех задач.
   * GET /tasks
   *
   * @return HTTP-ответ с HTML-страницей, содержащей список всех задач.
   */
  def get_all(): Action[AnyContent] = Action.async { implicit request =>
    withUserLogin { userLogin =>
      for {
        tasks <- TasksMethods.tasks(userLogin)
      } yield Ok(views.html.tasks.listOfTasks(tasks))
    }
  }

  /**
   * Действие для отображения информации о задаче по указанному идентификатору.
   * GET /tasks/:id
   *
   * @param id  Идентификатор задачи.
   * @return    HTTP-ответ с HTML-страницей, содержащей информацию о задаче.
   *            Если задача с указанным идентификатором не найдена, возвращает страницу с сообщением о не найденной задаче.
   */
  def get_task(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    withUserLogin { userLogin =>
      TasksMethods.readOneTask(id, userLogin).map { task =>
        Ok(views.html.tasks.taskDetails(task))
      }.recover {
        case _: NoSuchElementException =>
          NotFound(views.html.tasks.notFound("Задача с указанным идентификатором не найдена"))
      }
    }
  }

  /**
   * Действие для отображения страницы создания новой задачи.
   * GET /tasks/create
   *
   * @return HTTP-ответ с HTML-страницей для создания новой задачи.
   */
  def create(): Action[AnyContent] = Action { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)
    Ok(views.html.tasks.createTask(taskForm))
  }

  /**
   * Обработчик POST-запроса на создание новой задачи.
   * POST /tasks/create
   *
   * @return HTTP-ответ с результатом создания новой задачи.
   */
  def save(): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    withUserLogin { userLogin =>
      taskForm.bindFromRequest().fold(
        formWithErrors => Future.successful(BadRequest(views.html.tasks.createTask(formWithErrors))),
        formData => {
          val newTask = Task(login = userLogin, id = 0, formData.title, formData.description, formData.dueDate, formData.supplement, status = false)
            TasksMethods.createTask(userLogin, newTask.title, newTask.description, newTask.dueDate, newTask.supplement, newTask.status)
              .map {task =>
                TasksMethods.insertTask(task)
                Redirect(routes.ToDoListController.get_all()).withSession("userLogin" -> userLogin)
        }.recover {
              case _: Throwable =>
                val errorForm = taskForm.withGlobalError("Непредвиденная ошибка")
                BadRequest(views.html.tasks.createTask(errorForm))
              }
        }
      )
    }
  }

  /**
   * Действие для отображения страницы редактирования задачи по указанному идентификатору.
   * GET /tasks/edit/:id
   *
   * @param id  Идентификатор задачи.
   * @return    HTTP-ответ с HTML-страницей для редактирования задачи.
   *            Если задача с указанным идентификатором не найдена, возвращает страницу с сообщением о не найденной задаче.
   */
  def edit(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    withUserLogin { userLogin =>
      TasksMethods.readOneTask(id, userLogin).map { task =>
        Ok(views.html.tasks.editTask(editForm.fill(task)))
      }.recoverWith {
        case _: NoSuchElementException =>
          Future.successful(NotFound(views.html.tasks.notFound("Задача с указанным идентификатором не найдена")))
      }
    }
  }

  /**
   * Обработчик POST-запроса на обновление задачи.
   * POST /tasks/edit
   *
   * @return HTTP-ответ с результатом обновления задачи.
   */
  def update(): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    withUserLogin { userLogin =>
      editForm.bindFromRequest().fold(
        formWithErrors => Future.successful(BadRequest(views.html.tasks.editTask(formWithErrors))),
        formData => {
          val updatedTask = Task(login = userLogin, formData.id, formData.title, formData.description, formData.dueDate, formData.supplement, status = false)
          TasksMethods.editTask(updatedTask, userLogin).map { _ =>
            Redirect(routes.ToDoListController.get_all()).withSession("userLogin" -> userLogin)
          }
        }
      )
    }
  }

  /**
   * Действие для удаления задачи по указанному идентификатору.
   * GET /tasks/delete/:id
   *
   * @param id  Идентификатор задачи для удаления.
   * @return    HTTP-ответ с результатом удаления задачи.
   */
  def delete_one(id: Int): Action[AnyContent] = Action.async { implicit request =>
    implicit val messages: Messages = messagesApi.preferred(request)

    withUserLogin { userLogin =>
      for {
        doneTask <- TasksMethods.readOneTask(id, userLogin)
        _ <- Future(TasksMethods.deleteTask(doneTask))
        remainingTasks <- TasksMethods.tasks(userLogin)
      } yield Ok(views.html.tasks.deleteResult(doneTask, remainingTasks))
    }.recover {
      case _: NoSuchElementException =>
        NotFound(views.html.tasks.notFound("Задача с указанным идентификатором не найдена"))
    }.recover {
      case ex =>
        InternalServerError(s"Произошла ошибка: ${ex.getMessage}")
    }
  }

  /**
   * Действие для отображения списка удаленных задач.
   * GET /deleted
   *
   * @return HTTP-ответ с HTML-страницей, содержащей список удаленных задач.
   */
  def deleted(): Action[AnyContent] = Action.async { implicit request =>
    withUserLogin { userLogin =>
      for {
        doneTask <- TasksMethods.doneTasks(userLogin)
      } yield Ok(views.html.tasks.doneTasks(doneTask))
    }
  }

  /**
   * Действие для очистки указанной таблицы в базе данных.
   * GET /clear/:tableName
   *
   * @param table  Имя таблицы, которую необходимо очистить.
   * @return       HTTP-ответ с редиректом на страницу со списком всех задач.
   */
  def clear(table: String): Action[AnyContent] = Action.async { implicit request =>
    withUserLogin { userLogin =>
      TasksMethods.clearTable(table, userLogin).map { _ =>
        Redirect(routes.ToDoListController.get_all()).withSession("userLogin" -> userLogin)
      }
    }
  }

  /**
   * Debug метод (пустое тело метода).
   * Этот метод не имеет реализации и используется для отладки.
   * Не используйте этот метод в финальной версии приложения.
   *
   * @return  HTTP-ответ (пустое тело).
   */
  def debugToDoList(): Action[AnyContent] = ???
}
