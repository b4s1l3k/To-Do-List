
package views.html.users

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object registerUser extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[Models.Users.User],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userForm: Form[Models.Users.User])(implicit messages: Messages, request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.90*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Регистрация пользователя</title>
        <style>
                body """),format.raw/*8.22*/("""{"""),format.raw/*8.23*/("""
                    """),format.raw/*9.21*/("""font-family: Arial, sans-serif;
                    background-color: #f2f2f2;
                """),format.raw/*11.17*/("""}"""),format.raw/*11.18*/("""

                """),format.raw/*13.17*/(""".form-container """),format.raw/*13.33*/("""{"""),format.raw/*13.34*/("""
                    """),format.raw/*14.21*/("""max-width: 400px;
                    margin: 0 auto;
                    padding: 20px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    background-color: #fff;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                """),format.raw/*21.17*/("""}"""),format.raw/*21.18*/("""

                """),format.raw/*23.17*/(""".form-container h2 """),format.raw/*23.36*/("""{"""),format.raw/*23.37*/("""
                    """),format.raw/*24.21*/("""text-align: center;
                    margin-bottom: 20px;
                """),format.raw/*26.17*/("""}"""),format.raw/*26.18*/("""

                """),format.raw/*28.17*/(""".form-group """),format.raw/*28.29*/("""{"""),format.raw/*28.30*/("""
                    """),format.raw/*29.21*/("""margin-bottom: 15px;
                """),format.raw/*30.17*/("""}"""),format.raw/*30.18*/("""

                """),format.raw/*32.17*/(""".form-group label """),format.raw/*32.35*/("""{"""),format.raw/*32.36*/("""
                    """),format.raw/*33.21*/("""display: block;
                    font-weight: bold;
                    margin-bottom: 5px;
                """),format.raw/*36.17*/("""}"""),format.raw/*36.18*/("""

                """),format.raw/*38.17*/(""".form-group input """),format.raw/*38.35*/("""{"""),format.raw/*38.36*/("""
                    """),format.raw/*39.21*/("""width: 100%;
                    padding: 10px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    font-size: 16px;
                    margin-left: -25px;
                """),format.raw/*45.17*/("""}"""),format.raw/*45.18*/("""

                """),format.raw/*47.17*/(""".form-group button """),format.raw/*47.36*/("""{"""),format.raw/*47.37*/("""
                    """),format.raw/*48.21*/("""width: 100%;
                    padding: 10px;
                    background-color: #007bff;
                    color: #fff;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    font-size: 16px;
                """),format.raw/*56.17*/("""}"""),format.raw/*56.18*/("""

                """),format.raw/*58.17*/(""".form-group button:hover """),format.raw/*58.42*/("""{"""),format.raw/*58.43*/("""
                    """),format.raw/*59.21*/("""background-color: #0056b3;
                """),format.raw/*60.17*/("""}"""),format.raw/*60.18*/("""
        """),format.raw/*61.9*/("""</style>
    </head>
    <body>
        <div class="form-container">
            <h2>Регистрация пользователя</h2>
            """),_display_(/*66.14*/helper/*66.20*/.form(action = helper.CSRF(routes.MainPageController.saveUser()))/*66.85*/ {_display_(Seq[Any](format.raw/*66.87*/("""
                """),_display_(/*67.18*/helper/*67.24*/.CSRF.formField),format.raw/*67.39*/("""
                """),format.raw/*68.17*/("""<div class="form-group">
                """),_display_(/*69.18*/helper/*69.24*/.inputText(userForm("Логин"))),format.raw/*69.53*/("""
                """),format.raw/*70.17*/("""</div>
                <div class="form-group">
                """),_display_(/*72.18*/helper/*72.24*/.inputPassword(userForm("Пароль"))),format.raw/*72.58*/("""
                """),format.raw/*73.17*/("""</div>
                <div class="form-group">
                    <button type="submit">Зарегистрироваться</button>
                </div>
            """)))}),format.raw/*77.14*/("""
        """),format.raw/*78.9*/("""</div>
    </body>
</html>
"""))
      }
    }
  }

  def render(userForm:Form[Models.Users.User],messages:Messages,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(userForm)(messages,request)

  def f:((Form[Models.Users.User]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (userForm) => (messages,request) => apply(userForm)(messages,request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/users/registerUser.scala.html
                  HASH: 40afef879a8367da468535188adf67e54b332eba
                  MATRIX: 782->1|965->89|995->93|1146->217|1174->218|1223->240|1348->337|1377->338|1425->358|1469->374|1498->375|1548->397|1877->698|1906->699|1954->719|2001->738|2030->739|2080->761|2187->840|2216->841|2264->861|2304->873|2333->874|2383->896|2449->934|2478->935|2526->955|2572->973|2601->974|2651->996|2793->1110|2822->1111|2870->1131|2916->1149|2945->1150|2995->1172|3254->1403|3283->1404|3331->1424|3378->1443|3407->1444|3457->1466|3785->1766|3814->1767|3862->1787|3915->1812|3944->1813|3994->1835|4066->1879|4095->1880|4132->1890|4292->2023|4307->2029|4381->2094|4421->2096|4467->2115|4482->2121|4518->2136|4564->2154|4634->2197|4649->2203|4699->2232|4745->2250|4839->2317|4854->2323|4909->2357|4955->2375|5144->2533|5181->2543
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|36->11|36->11|38->13|38->13|38->13|39->14|46->21|46->21|48->23|48->23|48->23|49->24|51->26|51->26|53->28|53->28|53->28|54->29|55->30|55->30|57->32|57->32|57->32|58->33|61->36|61->36|63->38|63->38|63->38|64->39|70->45|70->45|72->47|72->47|72->47|73->48|81->56|81->56|83->58|83->58|83->58|84->59|85->60|85->60|86->61|91->66|91->66|91->66|91->66|92->67|92->67|92->67|93->68|94->69|94->69|94->69|95->70|97->72|97->72|97->72|98->73|102->77|103->78
                  -- GENERATED --
              */
          