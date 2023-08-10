package Models.Users

import play.api.data.Form
import play.api.data.Forms._


object UserForm {

  val userForm: Form[User] = Form {
    mapping(
      "userId" -> ignored(0),
      "Логин" -> nonEmptyText,
      "Пароль" -> nonEmptyText
    )(User.apply)(User.unapply)

  }



}
