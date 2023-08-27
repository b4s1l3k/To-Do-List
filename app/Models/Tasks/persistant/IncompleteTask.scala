package Models.Tasks.persistant

import java.time.LocalDate

case class IncompleteTask(login: String,
                          title: String,
                          description: String,
                          dueDate: LocalDate,
                          supplement: Option[String])
