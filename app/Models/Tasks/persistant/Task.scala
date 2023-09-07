package Models.Tasks.persistant

import io.swagger.annotations.{ApiModel, ApiModelProperty}

import java.time.LocalDate

@ApiModel
final case class Task(
                       login: String,
                       @ApiModelProperty(
                         dataType = "int",
                         example = "Optional[Int]",
                       )
                       id: Option[Int],
                       title: String,
                       description: String,
                       @ApiModelProperty(
                         dataType = "Date",
                         example = "yyyy-mm-dd",
                         required = true
                       )
                       dueDate: LocalDate,
                       supplement: Option[String],

                       status: Boolean
                     )

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
