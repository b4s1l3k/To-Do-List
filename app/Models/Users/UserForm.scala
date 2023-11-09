package Models.Users

import play.api.data.Form
import play.api.data.Forms._
import persistant._


object UserForm {

  val userForm: Form[User] = Form {
    mapping(
      "login" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)

  }


}
