package Models.Tasks.persistant

import java.time.LocalDate

final case class Task(login: String,
                id: Option[Int],
                title: String,
                description: String,
                dueDate: LocalDate,
                supplement: Option[String],
                status: Boolean)

object SlickTablesTask {

  import slick.jdbc.PostgresProfile.api._

  class TaskTable(tag: Tag) extends Table[Task](tag, Some("tasks"), "All Tasks") {
    def login = column[String]("login")

    def id = column[Option[Int]]("id")

    def title = column[String]("title")

    def description = column[String]("description")

    def dueDate = column[LocalDate]("deadline")

    def supplement = column[Option[String]]("supplement")

    def status = column[Boolean]("status")

    override def * = (login, id, title, description, dueDate, supplement, status) <> (Task.tupled, Task.unapply)
  }

  lazy val taskTable = TableQuery[TaskTable]
}
