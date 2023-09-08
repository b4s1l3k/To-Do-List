package Models.Tasks.errors

case class IdChangeError(id: Int)(message: String = s"Use the past id: $id") extends Exception(message)
