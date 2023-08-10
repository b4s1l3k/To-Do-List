package Models

import Models.Connection.Connection
import Models.Users.SlickTablesUser._
import Models.Tasks.SlickTablesTask._

import scala.util.{Failure, Success}
import slick.jdbc.PostgresProfile.api._
import Models.PrivateExecutionContext._

object PrivateMethods extends App {

  private def clearUsers(): Unit = {
    // Формируем запрос на удаление всех пользователей.
    val finalQuery = DBIO.seq(
      taskTable.delete,
      userTable.delete
    )
    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(finalQuery).onComplete {
      case Success(_) => println(s"All users have been deleted")
      case Failure(ex) => println(s"Failed to delete users: ${ex.getMessage}")
    }
    Thread.sleep(5000)
  }

  private def deleteUser(login: String): Unit = {
    val finalQuery = DBIO.seq(
      taskTable.filter(_.login === login).delete,
      userTable.filter(_.login === login).delete
    )

    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(finalQuery).onComplete {
      case Success(_) => println(s"The user with identifier $login has been successfully deleted.")
      case Failure(ex) => println(s"Failed to delete the user: ${ex.getMessage}")
    }
    Thread.sleep(5000)
  }

  clearUsers()
}




