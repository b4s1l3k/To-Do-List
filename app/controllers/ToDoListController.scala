package controllers

import Models.Tasks.persistant._
import Models.PrivateExecutionContext._
import play.api.mvc._
import play.api.libs.json._

import javax.inject._
import scala.concurrent.Future
import scala.language.reflectiveCalls
import scala.util.Try

@Singleton
class ToDoListController @Inject()(taskJSON: TaskJSONImpl, cc: ControllerComponents)
  extends AbstractController(cc) {

  implicit val format: OFormat[Task] = Json.format[Task]

  private object TasksForm {

    def NoSuchElementExceptionError: Result =
      NotFound(Json.obj("error" -> "The task with the specified identifier was not found."))

    def UnexpectedError(error: Throwable): Result =
      InternalServerError(Json.obj("error" -> s"Unforeseen error: ${error.getMessage}."))

    def WrongDate: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("dataError" -> "Deadline date earlier than the current date"))
      }

    def InvalidJSONFormat: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("error" -> "Invalid JSON format"))
      }

    def WrongLogin: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("error" -> "Failed to update task: Wrong login"))
      }

    def FailedAddTask(ex: Throwable): Result =
      BadRequest(Json.obj("failed" -> s"Failed to add a task: ${ex.getMessage}"))

    def FailedClearTask(ex: Throwable): Result =
      BadRequest(Json.obj("failed" -> s"Failed to clear tasks: ${ex.getMessage}"))
  }


  private def withUserLogin(f: String => Future[Result])(implicit request: Request[AnyContent]): Future[Result] = {
    request.cookies.get("userLogin") match {
      case Some(cookie) => f(cookie.value)
      case None => Future.successful(Unauthorized(Json.obj("error" -> "Need to login")))
    }
  }

  def getTasks: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskJSON.getTasks(userLogin).map { tasks =>
        Ok(tasks)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  def getDoneTasks: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskJSON.getDoneTasks(userLogin).map { doneTasks =>
        Ok(doneTasks)
      }.recover {
        case ex =>
          UnexpectedError(ex)
      }
    }
  }

  def getTask(id: Int): Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskJSON.getOneTask(id, userLogin).map { task =>
        Ok(task)
      }.recover {
        case _: NoSuchElementException =>
          NoSuchElementExceptionError
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  private def saveOrUpdate[T <: {def login: String}](action: T => Future[Result])(implicit request: Request[AnyContent],
                                                                                  reads: Reads[T]): Future[Result] = {
    import TasksForm._


    // Написать Success и Failure вместо getOrElse
    val taskJson = Try(request.body.asJson.get).getOrElse(Json.obj())
    if (taskJson == Json.obj()) {
      InvalidJSONFormat
    } else {
      withUserLogin { userLogin =>
        taskJSON.validDueDate(taskJson) match {
          case Left(flag) if flag =>
            WrongDate
          case Left(_) =>
            taskJson.validate[T] match {
              case JsSuccess(task, _) if task.login == userLogin =>
                action(task)
              case _ =>
                WrongLogin
            }
          case Right(error) =>
            Future.successful(BadRequest(error))
        }
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  def save: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._
    implicit val writes: Writes[IncompleteTask] = Json.writes[IncompleteTask]
    implicit val reads: Reads[IncompleteTask] = Json.reads[IncompleteTask]

    saveOrUpdate[IncompleteTask] { incompleteTask =>
      // Убрать превращение в JSON
      taskJSON.createTask(Json.toJson(incompleteTask)).flatMap { newTask =>
        taskJSON.insertTask(newTask).map { result =>
          Created(result)
        }.recover {
          case ex: Throwable =>
            FailedAddTask(ex)
        }
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  def update: Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    saveOrUpdate[Task] { updatedTask =>
      // Убрать превращение в JSON
      taskJSON.updateTask(Json.toJson(updatedTask)).map { result =>
        Ok(result)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }


  def deleteOneTask(id: Int): Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskJSON.deleteTask(id, userLogin).map { result =>
        Ok(result)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  def clear(tableName: String): Action[AnyContent] = Action.async { implicit request =>
    import TasksForm._

    withUserLogin { userLogin =>
      taskJSON.deleteTasks(userLogin, tableName).map { result =>
        Ok(result)
      }.recover {
        case ex =>
          FailedClearTask(ex)
      }
    }
  }


  def debugToDoList: Action[AnyContent] = ???

}
