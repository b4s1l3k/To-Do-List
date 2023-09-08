package Models.Tasks.service

import Models.Tasks.persistant.{Task, TaskRepositoryImpl}
import Models.PrivateExecutionContext._
import Models.Tasks.errors.IdChangeError

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.Future

trait TaskService {

  def createTask(login: String,
                 title: String,
                 description: String,
                 dueDate: LocalDate,
                 supplement: Option[String],
                 status: Boolean): Future[Task]

  def updateTask(id: Int, task: Task, login: String): Future[Unit]
}

class TaskServiceImpl @Inject()(taskRepository: TaskRepositoryImpl) extends TaskService {

  /**
   * Мето для создания новой задачи для указанного пользователя.
   *
   * @param login       Логин пользователя.
   * @param title       Заголовок задачи.
   * @param description Описание задачи.
   * @param dueDate     Дата выполнения задачи.
   * @param supplement  Дополнительная информация к задаче.
   * @param status      Статус задачи (активная/завершенная).
   * @return Future с созданной задачей.
   */
  override def createTask(login: String,
                          title: String,
                          description: String,
                          dueDate: LocalDate,
                          supplement: Option[String],
                          status: Boolean): Future[Task] = {
    taskRepository.getTasks(login).map { listOfTasks =>
      val nextTaskId = if (listOfTasks.isEmpty || listOfTasks.headOption.exists(_.id.getOrElse(0) != 1)) {
        1
      } else {
        listOfTasks.map(_.id.getOrElse(0)).max + 1
      }
      Task(login, Some(nextTaskId), title, description, dueDate, supplement, status)
    }
  }

  /**
   * Метод для обновления существующей задачи для указанного пользователя.
   *
   * @param task  Обновленная информация о задаче.
   * @param login Логин пользователя.
   * @return Future без значения, завершающийся успешно при успешном обновлении задачи.
   *         В случае отсутствия задачи с указанным идентификатором для указанного пользователя,
   *         Future завершится ошибкой NoSuchElementException.
   */
  override def updateTask(id: Int, task: Task, login: String): Future[Unit] = {
    if (id == task.id.get) {
      taskRepository.getTasks(login).map { allTasks =>
        allTasks.find(_.id.get == id) match {
          case Some(_) => taskRepository.updateTask(id, task)
          case None =>
            val errorMessage = s"Task with ID=${task.id} not found for user with Login=$login"
            println(errorMessage)
            Future.failed(throw new NoSuchElementException(errorMessage))
        }
      }
    } else {
      println("The user tried to change the task id.")
      Future.failed(IdChangeError(id)())
    }

  }
}
