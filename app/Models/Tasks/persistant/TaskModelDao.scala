package Models.Tasks.persistant

import Models.Tasks.persistant.SlickTablesTask._
import slick.dbio.{DBIOAction, Effect, NoStream}
import slick.sql.{FixedSqlAction, SqlAction}

import javax.inject.Inject
import slick.jdbc.PostgresProfile.api._

trait TaskModelDao {
  def getTasks(login: String): FixedSqlAction[Seq[Task], NoStream, Effect.Read]

  def getDoneTasks(login: String): FixedSqlAction[Seq[Task], NoStream, Effect.Read]

  def insertTask(task: Task): FixedSqlAction[Int, NoStream, Effect.Write]

  def deleteTask(task: Task): FixedSqlAction[Int, NoStream, Effect.Write]

  def getOneTask(id: Int, login: String): SqlAction[Option[Task], NoStream, Effect.Read]

  def updateTask(task: Task, login: String): FixedSqlAction[Int, NoStream, Effect.Write]

  def deleteTasks(tasks: Seq[Task], login: String): DBIOAction[Unit, NoStream, Effect.Write]

  def deleteDoneTasks(login: String): FixedSqlAction[Int, NoStream, Effect.Write]

}

class TaskModelDaoImpl @Inject() extends TaskModelDao {

  /**
   * Метод получает список активных задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Запрос к базе данных для получения списка активных задач.
   */
  override def getTasks(login: String): FixedSqlAction[Seq[Task], NoStream, Effect.Read] =
    taskTable
      .filter(task => task.login === login && task.status === false)
      .sortBy(_.id)
      .result

  /**
   * Метод получает список завершенных задач для указанного пользователя.
   *
   * @param login Логин пользователя.
   * @return Запрос к базе данных для получения списка завершенных задач.
   */
  override def getDoneTasks(login: String): FixedSqlAction[Seq[Task], NoStream, Effect.Read] =
    taskTable
      .filter(task => task.login === login && task.status === true)
      .result

  /**
   * Метод вставляет новую задачу в базу данных.
   *
   * @param task Задача для вставки.
   * @return Запрос к базе данных для вставки задачи.
   */
  override def insertTask(task: Task): FixedSqlAction[Int, NoStream, Effect.Write] =
    taskTable += task

  /**
   * Метод удаляет задачу из базы данных.
   *
   * @param task Задача для удаления.
   * @return Запрос к базе данных для удаления задачи.
   */
  override def deleteTask(task: Task): FixedSqlAction[Int, NoStream, Effect.Write] =
    taskTable
      .filter(t => t.login === task.login && t.id === task.id && t.status === false)
      .update {
        task.copy(id = None, status = true)
      }

  /**
   * Метод получает одну задачу по указанному идентификатору и логину пользователя.
   *
   * @param id    Идентификатор задачи.
   * @param login Логин пользователя.
   * @return Запрос к базе данных для получения одной задачи.
   */
  override def getOneTask(id: Int, login: String): SqlAction[Option[Task], NoStream, Effect.Read] =
    taskTable
      .filter(task => task.login === login && task.id === id)
      .result
      .headOption

  /**
   * Метод обновляет существующую задачу в базе данных.
   *
   * @param task  Задача для обновления.
   * @param login Логин пользователя.
   * @return Запрос к базе данных для обновления задачи.
   */
  override def updateTask(task: Task, login: String): FixedSqlAction[Int, NoStream, Effect.Write] =
    taskTable
      .filter(t => t.login === login && t.id === task.id)
      .update(task)

  /**
   * Метод удаляет список задач из базы данных.
   *
   * @param tasks Список задач для удаления.
   * @param login Логин пользователя.
   * @return Действие для удаления задач.
   */
  override  def deleteTasks(tasks: Seq[Task], login: String): DBIOAction[Unit, NoStream, Effect.Write] = {

    val updatedTasks = tasks.filter(_.status == false).map(_.copy(id = None, status = true))
    DBIO.seq(
      taskTable ++= updatedTasks,
      taskTable.filter(task => task.login === login.bind && task.status === false).delete
    )
  }

  /**
   * Метод удаляет завершенные задачи пользователя из базы данных.
   *
   * @param login Логин пользователя.
   * @return Запрос к базе данных для удаления завершенных задач.
   */
  override def deleteDoneTasks(login: String): FixedSqlAction[Int, NoStream, Effect.Write] =
  taskTable
    .filter(task => task.login === login && task.status === true)
    .delete

}
