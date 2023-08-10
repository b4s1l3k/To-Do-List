package Models.Users

import play.api.data.Form
import play.api.data.Forms._


object UserForm {

  val userForm: Form[User] = Form {
    mapping(
      "Логин" -> nonEmptyText,
      "Пароль" -> nonEmptyText
    )(User.apply)(User.unapply)

  }



}
