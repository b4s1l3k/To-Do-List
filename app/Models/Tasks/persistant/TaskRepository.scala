package Models.Tasks.persistant

import Models.Connection.Connection

import javax.inject.Inject
import scala.concurrent.Future
import scala.util.{Failure, Success}

import Models.PrivateExecutionContext._

trait TaskRepository {

  def getTasks(login: String): Future[Seq[Task]]

  def getDoneTasks(login: String): Future[Seq[Task]]

  def insertTask(task: Task): Unit

  def deleteTask(task: Task): Unit

  def getOneTask(id: Int, login: String): Future[Task]

  def deleteTasks(tableName: String, login: String): Future[Unit]

  def updateTask(task: Task, login: String): Unit

}

class TaskRepositoryImpl @Inject()(taskModelDao: TaskModelDaoImpl) extends TaskRepository {

  /**
   * Метод для получения списка активных задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Future со списком активных задач.
   */
  override def getTasks(login: String): Future[Seq[Task]] =
    Connection.db.run(taskModelDao.getTasks(login)).andThen {
      case Success(tasks) => println(s"The following tasks were retrieved from the database: $tasks.")
      case Failure(ex) => println(s"Error when retrieving tasks from the database: ${ex.getMessage}")
    }

  /**
   * Метод для получения списка завершенных задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Future со списком завершенных задач.
   */
  override def getDoneTasks(login: String): Future[Seq[Task]] =
    Connection.db.run(taskModelDao.getDoneTasks(login)).andThen {
      case Success(tasks) => println(s"The following done tasks were retrieved from the database: $tasks.")
      case Failure(ex) => println(s"Error when retrieving done tasks from the database: ${ex.getMessage}")
    }

  /**
   * Метод для вставки новой задачи в базу данных.
   *
   * @param task Задача для вставки.
   */
  override def insertTask(task: Task): Unit =
    Connection.db.run(taskModelDao.insertTask(task)).onComplete {
      case Success(_) => println(s"A new task has been added! Title: ${task.title}")
      case Failure(ex) => println(s"Failed to add a task: ${ex.getMessage}")
    }

  /**
   * Метод для удаления задачи из базы данных.
   *
   * @param task Задача для удаления.
   */
  override def deleteTask(task: Task): Unit =
    Connection.db.run(taskModelDao.deleteTask(task)).onComplete {
      case Success(_) => println(s"The task with identifier ${task.id} has been successfully deleted.")
      case Failure(ex) => println(s"Failed to delete the task: ${ex.getMessage}")
    }

  /**
   * Метод для получения одной задачи по указанному идентификатору и логину пользователя.
   *
   * @param id    Идентификатор задачи.
   * @param login Логин пользователя.
   * @return Future с одной задачей или ошибкой, если задача не найдена.
   */
  override def getOneTask(id: Int, login: String): Future[Task] =
    Connection.db.run(taskModelDao.getOneTask(id, login)).flatMap {
      case Some(task) =>
        println(s"The following task was retrieved from the database: $task.")
        Future(task)
      case None =>
        val errorMessage = s"The task with ID=$id was not found for user with Login=$login"
        println(errorMessage)
        Future.failed(new NoSuchElementException(errorMessage))
    }

  /**
   * Метод удаляет задачи из базы данных в зависимости от указанного типа задачи.
   *
   * @param tableName Название таблицы для удаления задач.
   * @param login     Логин пользователя.
   * @return Future без значения.
   */
  override def deleteTasks(tableName: String, login: String): Future[Unit] = {
    import TableNames._
    tableName match {
      case DeleteTasks =>
        getTasks(login).flatMap { tasks =>
          val updatedTasks = tasks
            .filter(_.status == false)
            .map(task => task.copy(id = None, status = true))
          Connection.db.run(taskModelDao.deleteTasks(updatedTasks, login)).map(_ => ()).recover {
            case ex => println(s"Failed to clear tasks: ${ex.getMessage}")
          }
        }
      case DeleteDoneTasks =>
        Connection.db.run(taskModelDao.deleteDoneTasks(login)).map(_ => ()).recover {
          case ex => println(s"Failed to clear done tasks: ${ex.getMessage}")
        }
    }
  }

  /**
   * Метод для обновления сущетсвующей задачи в базе данных.
   *
   * @param task  Задача для обновления.
   * @param login Логин пользователя.
   */
  override def updateTask(task: Task, login: String): Unit = {
    Connection.db.run(taskModelDao.updateTask(task, login)).onComplete {
      case Success(_) => println(s"Task updated! Title: ${task.title}")
      case Failure(ex) => println(s"Failed to update the task: ${ex.getMessage}")
    }
  }

}

/**
 * Объект для хранения констант с названиями таблиц задач.
 */
private object TableNames {
  val DeleteTasks = "clearTasks"
  val DeleteDoneTasks = "clearDoneTasks"
}