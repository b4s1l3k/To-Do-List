package Models.Users

import slick.jdbc.PostgresProfile.api._

import org.mindrot.jbcrypt.BCrypt

import scala.concurrent.Future
import scala.util.{Failure, Success}
import Models.PrivateExecutionContext._
import Models.Connection._

object UsersMethods {

  /**
   * Метод для получения списка всех пользователей из базы данных.
   *
   * @return Фьючерс со списком пользователей.
   */
  def users: Future[Seq[User]] = {
    // Формируем запрос к базе данных для получение всех пользователей.
    val queryDescription = SlickTablesUser.userTable.result
    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(queryDescription).andThen {
      case Success(users) => println(s"The following users were retrieved from the database: $users.")
      case Failure(ex) => println(ex)
    }
  }

  /**
   * Метод для добавления нового пользователя в базу данных.
   *
   * @param user Добавляемый пользователь.
   */
  def insertUser(user: User): Unit = {
    // Формируем запрос на вставку нового пользователя в базу данных.
    val queryDescription = SlickTablesUser.userTable += user
    // Выполняем запрос к базе данных и выводим результат или ошибку.
    Connection.db.run(queryDescription).onComplete {
      case Success(_) => println(s"A new user has been added! Login: ${user.login}")
      case Failure(ex) => println(s"Failed to add a user: ${ex.getMessage}")
    }
  }

  /**
   * Метод для проверки уникальность логина в базе данных.
   *
   * @param login Логин пользователя для проверки.
   * @return Фьючерс, который завершится со значением true, если логин уникален, и false в противном случае.
   */
  def checkUserByLogin(login: String): Future[Boolean] = {
    // Формируем запрос к базе данных для проверки наличия пользователя с указанным логином.
    val queryDescription = SlickTablesUser.userTable.filter(_.login === login).exists.result
    // Выполняем запрос к базе данных и возвращаем результат (true, если такой логин уже занят, и false, если логин уникален).
    Connection.db.run(queryDescription)
  }

  /**
   * Метод для проверки соответствия логина и пароля пользователя в базе данных.
   *
   * @param login    Логин пользователя для проверки.
   * @param password Пароль пользователя для проверки.
   * @return Фьючерс, который завершится со значением true, если логин и пароль соответствуют,
   *         и false в противном случае.
   */
  def checkUserPassword(login: String, password: String): Future[Boolean] = {
    // Формируем запрос к базе данных для получения хеша пароля пользователя.
    val queryPasswordHash = SlickTablesUser.userTable
      .filter(user => user.login === login)
      .map(_.password)
      .result
      .head

    // Выполняем запрос к базе данных для получения хеша пароля.
    val passwordHashFuture: Future[String] = Connection.db.run(queryPasswordHash)

    // Проверяем соответствие пароля.
    passwordHashFuture.map { hashedPassword =>
      BCrypt.checkpw(password, hashedPassword)
    }
  }

  def hashingPassword(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt(10))
  }

}


