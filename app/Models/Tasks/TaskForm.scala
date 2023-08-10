package Models.Tasks

import play.api.data.Form
import play.api.data.Forms._
import Models.Tasks._

object TaskForm {

  val taskForm: Form[Task] = Form {
    mapping(
      "login" -> ignored(""),
      "id" -> optional(ignored(0)),
      "Задача" -> nonEmptyText,
      "Описание" -> nonEmptyText,
      "Дедлайн" -> localDate,
      "Дополнение" -> optional(text),
      "Выполнена" -> ignored(false)
    )(Task.apply)(Task.unapply)
  }

  val editForm: Form[Task] = Form {
    mapping(
      "login" -> text,
      "id" -> optional(number),
      "Задача" -> nonEmptyText,
      "Описание" -> nonEmptyText,
      "Дедлайн" -> localDate,
      "Дополнение" -> optional(text),
      "Выполнена" -> ignored(false)
    )(Task.apply)(Task.unapply)
  }

}
