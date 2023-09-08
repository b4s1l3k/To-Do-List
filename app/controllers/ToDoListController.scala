package controllers

import Models.Tasks.persistant._
import Models.PrivateExecutionContext._
import Models.Tasks.errors.IdChangeError
import Models.Tasks.service.TaskServiceImpl
import io.swagger.annotations._
import play.api.mvc._
import play.api.libs.json._

import javax.inject._
import scala.concurrent.Future
import scala.language.reflectiveCalls
import scala.util.{Failure, Success, Try}


@Api(value = "Task Controller")
@Singleton
class ToDoListController @Inject()(taskJSON: TaskJSONImpl, taskService: TaskServiceImpl, cc: ControllerComponents)
  extends AbstractController(cc) {
  implicit val format: OFormat[Task] = Json.format[Task]


  private object Forms {

    def NoSuchElementExceptionError: Result =
      NotFound(Json.obj("error" -> "The task with the specified identifier was not found."))

    def UnexpectedError(error: Throwable): Result =
      InternalServerError(Json.obj("error" -> s"Unforeseen error: ${error.getMessage}."))

    def WrongDate: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("dataError" -> "Deadline date earlier than the current date"))
      }

    def WrongLogin: Future[Result] =
      Future.successful {
        BadRequest(Json.obj("error" -> "Failed to update task: Wrong login"))
      }

    def FailedAddTask(ex: Throwable): Result =
      BadRequest(Json.obj("failed" -> s"Failed to add a task: ${ex.getMessage}"))

    def FailedUpdateTask(ex: Throwable): Result =
      BadRequest(Json.obj("failed" -> s"Failed to update a task: ${ex.getMessage}"))

    def FailedClearTasks(ex: Throwable): Result =
      BadRequest(Json.obj("failed" -> s"Failed to clear tasks: ${ex.getMessage}"))
  }

  private def withUserLogin(f: String => Future[Result])(implicit request: Request[AnyContent]): Future[Result] = {
    request.cookies.get("userLogin") match {
      case Some(cookie) => f(cookie.value)
      case None => Future.successful(Unauthorized(Json.obj("error" -> "Need to login")))
    }
  }

  @ApiOperation(
    value = "Get all tasks",
    response = classOf[Task],
    responseContainer = "List",
    notes = "Get a list of all tasks for the authenticated user.")
  @ApiResponses(value = Array(
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  def getTasks: Action[AnyContent] = Action.async { implicit request =>
    import Forms._

    withUserLogin { userLogin =>
      taskJSON.getTasks(userLogin).map { tasks =>
        Ok(tasks)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }
  @ApiOperation(
    value = "Get done tasks",
    response = classOf[DoneTask],
    responseContainer = "List",
    notes = "Get a list of all completed tasks for the authenticated user.")
  @ApiResponses(Array(
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  def getDoneTasks: Action[AnyContent] = Action.async { implicit request =>
    import Forms._

    withUserLogin { userLogin =>
      taskJSON.getDoneTasks(userLogin).map { doneTasks =>
        Ok(doneTasks)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }

  @ApiOperation(
    value = "Get a task by ID",
    response = classOf[Task],
    notes = "Get details of a task by its ID for the authenticated user."
  )
  @ApiResponses(Array(
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 404, message = "The task with the specified identifier was not found."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  def getTask(@ApiParam(value = "Task ID", required = true) id: Int): Action[AnyContent] = Action.async { implicit request =>
    import Forms._

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
    import Forms._

    Try(request.body.asJson.get) match {
      case Failure(ex) => Future.successful(UnexpectedError(ex))
      case Success(taskJson) =>
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
        }.recover {
          case ex =>
            println(123)
            println(ex.getMessage)
            UnexpectedError(ex)
        }
    }
  }

  @ApiOperation(
    value = "Create a new task",
    notes = "Create a new task for the authenticated user."
  )
  @ApiResponses(Array(
    new ApiResponse(code = 201, message = "A new task has been added! Title: \"task title\"."),
    new ApiResponse(code = 400, message = "Failed to add a task: \"error\"."),
    new ApiResponse(code = 400, message = "Deadline date earlier than the current date."),
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      name = "Incomplete task data",
      value = "Incomplete Task data in JSON format",
      required = true,
      dataTypeClass = classOf[IncompleteTask],
      paramType = "body")
  ))
  def save: Action[AnyContent] = Action.async { implicit request =>
    import Forms._
    implicit val reads: Reads[IncompleteTask] = Json.reads[IncompleteTask]

    saveOrUpdate[IncompleteTask] { incompleteTask =>
      taskService.createTask(incompleteTask.login,
                             incompleteTask.title,
                             incompleteTask.description,
                             incompleteTask.dueDate,
                             incompleteTask.supplement,
                             status = false).flatMap { newTask =>
        taskJSON.insertTask(newTask).map { result =>
          Created(result)
        }.recover {
          case ex: Throwable =>
            FailedAddTask(ex)
        }
      }
    }.recover {
      case ex => UnexpectedError(ex)
    }
  }

  @ApiOperation(
    value = "Update an existing task",
    notes = "Update an existing task for the authenticated user.")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "The task have been updated."),
    new ApiResponse(code = 400, message = "Failed to update a task: \"error\"."),
    new ApiResponse(code = 401, message = "Need to login."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(
      name = "Task",
      value = "Task data in JSON format",
      required = true,
      dataTypeClass = classOf[Task],
      paramType = "body")
  ))
  def update(@ApiParam(value = "Task ID", required = true) id: Int): Action[AnyContent] = Action.async { implicit request =>
    import Forms._

    saveOrUpdate[Task] { updatedTask =>
      taskJSON.updateTask(id, Json.toJson(updatedTask)).map { result =>
        Ok(result)
      }.recover {
        case _: NoSuchElementException =>
          NoSuchElementExceptionError
        case ex: IdChangeError =>
          FailedUpdateTask(ex)
      }
    }.recover {
      case ex: IllegalStateException =>
        FailedUpdateTask(ex)
    }
  }

  @ApiOperation(
    value = "Delete a task by ID",
    notes = "Delete a task by its ID for the authenticated user.")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "The task has been successfully marked as done."),
    new ApiResponse(code = 404, message = "Task not found."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  def deleteOneTask(@ApiParam(value = "Task ID", required = true) id: Int): Action[AnyContent] = Action.async {
    implicit request =>
    import Forms._

    withUserLogin { userLogin =>
      taskJSON.deleteTask(id, userLogin).map { result =>
        Ok(result)
      }
    }.recover {
      case _: NoSuchElementException =>
        NoSuchElementExceptionError
      case ex =>
        UnexpectedError(ex)
    }
  }

  @ApiOperation(
    value = "Clear tasks",
    notes = "Clear completed or uncompleted tasks for the authenticated user.")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Tasks have been deleted."),
    new ApiResponse(code = 400, message = "Failed to clear tasks: \"error\"."),
    new ApiResponse(code = 500, message = "Unforeseen error: \"error\".")
  ))
  def clear(@ApiParam(name = "tasks or doneTasks", value = "Type of the task", required = true) tableName: String): Action[AnyContent] = Action.async {
    implicit request =>
    import Forms._

    withUserLogin { userLogin =>
      taskJSON.deleteTasks(userLogin, tableName).map { result =>
        Ok(result)
      }.recover {
        case ex =>
          FailedClearTasks(ex)
      }
    }.recover {
      case ex =>
        UnexpectedError(ex)
    }
  }
}
