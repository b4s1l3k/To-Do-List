package Models.Users.errors

case class DuplicatedAccountException() extends Exception {
  final val message: String = "Such an account already exists."
}
