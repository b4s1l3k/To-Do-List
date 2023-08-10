package Models.Tasks

import java.time.LocalDate

case class Task(login: String,
                id: Option[Int],
                title: String,
                description: String,
                dueDate: LocalDate,
                supplement: Option[String],
                status: Boolean)

object SlickTablesTask {

  import slick.jdbc.PostgresProfile.api._

  class TaskTable(tag: Tag) extends Table[Task](tag, Some("tasks"), "All Tasks") {
    def login = column[String]("Логин")

    def id = column[Option[Int]]("ID")

    def title = column[String]("Заголовок")

    def description = column[String]("Описание")

    def dueDate = column[LocalDate]("Дедлайн")

    def supplement = column[Option[String]]("Дополнение")

    def status = column[Boolean]("Выполнена")

    override def * = (login, id, title, description, dueDate, supplement, status) <> (Task.tupled, Task.unapply)
  }

  lazy val taskTable = TableQuery[TaskTable]
}
