package Models.Users.errors

case class DuplicatedAccountException() extends Exception {
  final val message: String = "Такой аккаунт уже существует"
}
