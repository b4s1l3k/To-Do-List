package Models.Tasks.persistant

import Models.Tasks.service.TaskServiceImpl
import play.api.libs.json._
import Models.PrivateExecutionContext._

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.Future

trait TaskJSON {

  def getTasks(login: String): Future[JsValue]

  def getDoneTasks(login: String): Future[JsValue]

  def insertTask(task: Task): Future[JsValue]

  def deleteTask(id: Int, login: String): Future[JsValue]

  def getOneTask(id: Int, login: String): Future[JsValue]

  def deleteTasks(login: String, tableName: String): Future[JsValue]

  def updateTask(dataJson: JsValue): Future[JsValue]

  def createTask(dataJson: JsValue): Future[Task]

  def validDueDate(dataJson: JsValue): Either[Boolean, JsObject]

}

class TaskJSONImpl @Inject()(taskRepository: TaskRepositoryImpl, taskService: TaskServiceImpl) extends TaskJSON {
  implicit val formatTask: OFormat[Task] = Json.format[Task]
  implicit val formatIncompleteTask: OFormat[IncompleteTask] = Json.format[IncompleteTask]


  override def getTasks(login: String): Future[JsValue] =
    taskRepository.getTasks(login).map { tasks =>
      Json.toJson(tasks)
    }

  override def getDoneTasks(login: String): Future[JsValue] =
    taskRepository.getDoneTasks(login).map { tasks =>
      Json.toJson(tasks)
    }

  override def createTask(dataJson: JsValue): Future[Task] =
    dataJson.validate[IncompleteTask] match {
      case JsSuccess(task, _) =>
        taskRepository.getTasks(task.login).map { listOfTasks =>
          val nextTaskId = if (listOfTasks.isEmpty || listOfTasks.headOption.exists(_.id.getOrElse(0) != 1)) {
            1
          } else {
            listOfTasks.map(_.id.getOrElse(0)).max + 1
          }
          Task(task.login, Some(nextTaskId), task.title, task.description, task.dueDate, task.supplement, status = false)
        }
      case JsError(errors) =>
        // Обработать ошибки чтения JSON, например, выбросить исключение или вернуть пустой Future
        Future.failed(new IllegalArgumentException(s"Invalid JSON: $errors"))
    }

  override def insertTask(task: Task): Future[JsValue] =
    taskRepository.insertTask(task).map { _ =>
      Json.obj("success" -> s"A new task has been added! Title: ${task.title}")
    }

  override def deleteTask(id: Int, login: String): Future[JsValue] =
    taskRepository.deleteTask(id, login).map {
      case Int => Json.obj("succes" -> s"The task with id $id has been successfully marked as done.")
      case ex: Throwable => Json.obj("failure" -> s"Failed to mark the task as done: ${ex.getMessage}")
    }


  override def getOneTask(id: Int, login: String): Future[JsValue] =
    taskRepository.getOneTask(id, login).map { task =>
      Json.toJson(task)
    }

  override def deleteTasks(login: String, tableName: String): Future[JsValue] =
    taskRepository.deleteTasks(tableName, login).map { _ =>
      Json.obj("success" -> s"The tasks have been deleted.")
    }

  override def updateTask(dataJson: JsValue): Future[JsValue] =
    dataJson.validate[Task] match {
      case JsSuccess(task, _) =>
        taskService.updateTask(task, task.login).map { _ =>
          Json.obj("success" -> s"The task have been updated.")
        }
      case JsError(_) => Future.successful(Json.obj("error" -> "Invalid JSON"))
    }

  override def validDueDate(dataJson: JsValue): Either[Boolean, JsObject] =
    (dataJson \ "dueDate").validate[LocalDate] match {
      case JsSuccess(dueDate, _) => Left(dueDate.isBefore(LocalDate.now()))
      case JsError(_) => Right(Json.obj("error" -> "Invalid task JSON"))
    }


}
