package Models.Tasks

import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate
import scala.concurrent.Future
import Models.PrivateExecutionContext._
import Models.Connection._

import scala.util.{Failure, Success}

object TasksMethods {

  /**
   * Создает новую задачу с указанными данными.
   *
   * @param login       Логин пользователя.
   * @param title       Заголовок задачи.
   * @param description Описание задачи.
   * @param dueDate     Дедлайн задачи.
   * @param supplement  Дополнительная информация (опционально).
   * @param status      Статус задачи (false - активна).
   * @return Future с созданной задачей.
   */
  def createTask(login: String, title: String, description: String, dueDate: LocalDate, supplement: Option[String], status: Boolean): Future[Task] = {
    tasks(login).map { listOfTasks =>
      val nextTaskId = if (listOfTasks.isEmpty || listOfTasks.headOption.exists(_.id != 1)) {
        1
      } else {
        listOfTasks.map(_.id).max + 1
      }
      Task(login, nextTaskId, title, description, dueDate, supplement, status)
    }
  }

  /**
   * Получает список активных задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Future с последовательностью активных задач.
   */
  def tasks(login: String): Future[Seq[Task]] = {
    val queryDescription = SlickTablesTask.taskTable
      .filter(task => task.login === login && task.status === false)
      .sortBy(_.id)
      .result

    Connection.db.run(queryDescription).andThen {
      case Success(tasks) => println(s"The following tasks were retrieved from the database: $tasks.")
      case Failure(ex) => println(ex.getMessage)
    }
  }

  /**
   * Получает список выполненых задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Future с последовательностью выполненых задач.
   */
  def doneTasks(login: String): Future[Seq[Task]] = {
    val queryDescription = SlickTablesTask.taskTable
      .filter(task => task.login === login && task.status === true)
      .result

    Connection.db.run(queryDescription).andThen {
      case Success(tasks) => println(s"The following tasks were retrieved from the database: $tasks.")
      case Failure(ex) => println(ex)
    }
  }

  /**
   * Вставляет новую задачу в базу данных.
   *
   * @param task Задача для вставки.
   * @return Future с результатом вставки.
   */
  def insertTask(task: Task): Unit = {
    val queryDescription = SlickTablesTask.taskTable += task

    Connection.db.run(queryDescription).onComplete {
      case Success(_) => println(s"A new task has been added! Title: ${task.title}")
      case Failure(ex) => println(s"Failed to add a task: ${ex.getMessage}")
    }
  }

  /**
   * Удаляет задачу из базы данных.
   *
   * @param task Задача для удаления.
   * @return Future с результатом удаления.
   */
  def deleteTask(task: Task): Unit = {
    val deletedTask = task.copy(id = 0, status = true)
    val queryDescription = SlickTablesTask.taskTable
      .filter(t => t.login === task.login && t.id === task.id && t.status === false)
      .update(deletedTask)

    Connection.db.run(queryDescription).onComplete {
      case Success(_) => println(s"The task with identifier ${task.id} has been successfully deleted.")
      case Failure(ex) => println(s"Failed to delete the task: ${ex.getMessage}")
    }
  }

  /**
   * Чтение задачи по указанному идентификатору.
   *
   * @param id    Идентификатор задачи.
   * @param login Логин пользователя.
   * @return Future с найденной задачей.
   *         Если задача не найдена, возвращает исключение NoSuchElementException.
   */
  def readOneTask(id: Int, login: String): Future[Task] = {
    tasks(login).flatMap { taskList =>
      taskList.find(_.id == id) match {
        case Some(task) =>
          println(s"The following task were retrieved from the database: $task.")
          Future.successful(task)
        case None =>
          val errorMessage = s"The task with ID=$id was not found for user with Login=$login"
          println(errorMessage)
          Future.failed(new NoSuchElementException(errorMessage))
      }
    }
  }

  /**
   * Обновляет данные задачи в базе данных.
   *
   * @param task  Задача с обновленными данными.
   * @param login Логин пользователя.
   * @return Future с результатом обновления.
   */
  def editTask(task: Task, login: String): Future[Unit] = {
    tasks(login).map { allTasks =>
      allTasks.find(_.id == task.id) match {
        case Some(_) =>
          val queryDescriptor = SlickTablesTask.taskTable
            .filter(_.id === task.id)
            .update(task)
          Connection.db.run(queryDescriptor).onComplete {
            case Success(_) => println(s"Task updated! Title: ${task.title}")
            case Failure(ex) => println(s"Failed to update the task: ${ex.getMessage}")
          }
        case None =>
          val errorMessage = s"Task with ID=${task.id} not found for user with Login=$login"
          println(errorMessage)
          Future.failed(new NoSuchElementException(errorMessage))
      }
    }
  }

  /**
   * Метод для очистки таблицы в базе данных.
   *
   * @param tableName Имя типа задач для очистки ("clearTasks" или "clearDoneTasks").
   * @param login     Логин пользователя.
   */
  def clearTable(tableName: String, login: String): Future[Unit] = {

    tableName match {
      case "clearTasks" =>

        tasks(login).flatMap { tasks =>
          val updatedTasks = tasks.filter(_.status == false).map(task => task.copy(id = 0, status = true))
          val updateQueries = DBIO.seq(
            SlickTablesTask.taskTable ++= updatedTasks,
            SlickTablesTask.taskTable.filter(task => task.login === login && task.status === false).delete
          )
          Connection.db.run(updateQueries).map(_ => ()).recover {
            case ex => println(s"Failed to clear tasks: ${ex.getMessage}")
          }
        }

      case "clearDoneTasks" =>

        val queryDescription = SlickTablesTask.taskTable
          .filter(task => task.login === login && task.status === true)
          .delete

        Connection.db.run(queryDescription).map(_ => ()).recover {
          case ex => println(s"Failed to clear done tasks: ${ex.getMessage}")
        }
    }
  }
  
}
