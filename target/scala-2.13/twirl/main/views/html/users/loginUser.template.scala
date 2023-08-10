
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

object loginUser extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[Models.Users.User],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userForm: Form[Models.Users.User])(implicit messages: Messages, request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.90*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Авторизация пользователя</title>
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
            <h2>Авторизация пользователя</h2>
            """),_display_(/*66.14*/helper/*66.20*/.form(action = helper.CSRF(routes.MainPageController.login()))/*66.82*/ {_display_(Seq[Any](format.raw/*66.84*/("""
                """),_display_(/*67.18*/helper/*67.24*/.CSRF.formField),format.raw/*67.39*/("""
                """),format.raw/*68.17*/("""<div class="form-group">
                """),_display_(/*69.18*/helper/*69.24*/.inputText(userForm("Логин"))),format.raw/*69.53*/("""
                """),format.raw/*70.17*/("""</div>
                <div class="form-group">
                """),_display_(/*72.18*/helper/*72.24*/.inputPassword(userForm("Пароль"))),format.raw/*72.58*/("""
                """),format.raw/*73.17*/("""</div>
                <div class="form-group">
                    <button type="submit">Войти</button>
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
                  SOURCE: app/views/users/loginUser.scala.html
                  HASH: 1b2238b29fdd5001484a85a993ada172a5c1567f
                  MATRIX: 779->1|962->89|992->93|1143->217|1171->218|1220->240|1345->337|1374->338|1422->358|1466->374|1495->375|1545->397|1874->698|1903->699|1951->719|1998->738|2027->739|2077->761|2184->840|2213->841|2261->861|2301->873|2330->874|2380->896|2446->934|2475->935|2523->955|2569->973|2598->974|2648->996|2790->1110|2819->1111|2867->1131|2913->1149|2942->1150|2992->1172|3251->1403|3280->1404|3328->1424|3375->1443|3404->1444|3454->1466|3782->1766|3811->1767|3859->1787|3912->1812|3941->1813|3991->1835|4063->1879|4092->1880|4129->1890|4289->2023|4304->2029|4375->2091|4415->2093|4461->2112|4476->2118|4512->2133|4558->2151|4628->2194|4643->2200|4693->2229|4739->2247|4833->2314|4848->2320|4903->2354|4949->2372|5125->2517|5162->2527
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|36->11|36->11|38->13|38->13|38->13|39->14|46->21|46->21|48->23|48->23|48->23|49->24|51->26|51->26|53->28|53->28|53->28|54->29|55->30|55->30|57->32|57->32|57->32|58->33|61->36|61->36|63->38|63->38|63->38|64->39|70->45|70->45|72->47|72->47|72->47|73->48|81->56|81->56|83->58|83->58|83->58|84->59|85->60|85->60|86->61|91->66|91->66|91->66|91->66|92->67|92->67|92->67|93->68|94->69|94->69|94->69|95->70|97->72|97->72|97->72|98->73|102->77|103->78
                  -- GENERATED --
              */
          