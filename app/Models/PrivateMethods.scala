package Models

import Models.Connection.Connection
import Models.Users.SlickTablesUser
import Models.Tasks.SlickTablesTask

import scala.util.{Failure, Success}
import slick.jdbc.PostgresProfile.api._
import Models.PrivateExecutionContext._

object PrivateMethods extends App{

  private def clearUsers(): Unit = {
    // Формируем запрос на удаление всех пользователей.
    val deleteTasks = SlickTablesTask.taskTable.delete
    val deleteUsers = SlickTablesUser.userTable.delete
    val finalQuery = DBIO.seq(deleteTasks, deleteUsers)
    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(finalQuery).onComplete {
      case Success(_) => println(s"All users have been deleted")
      case Failure(ex) => println(s"Failed to delete users: ${ex.getMessage}")
    }
    Thread.sleep(5000)
  }

  private def deleteUser(login: String): Unit = {
    val deleteTasksOfUser = SlickTablesTask.taskTable.filter(_.login === login).delete
    val deleteUser = SlickTablesUser.userTable.filter(_.login === login).delete
    val finalQuery = DBIO.seq(deleteTasksOfUser, deleteUser)

    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(finalQuery).onComplete {
      case Success(_) => println(s"The user with identifier $login has been successfully deleted.")
      case Failure(ex) => println(s"Failed to delete the user: ${ex.getMessage}")
    }
    Thread.sleep(5000)
  }


clearUsers()
}




