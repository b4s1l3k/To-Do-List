
package views.html

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

object welcomePage extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Welcome page</title>
        <style>
                body """),format.raw/*8.22*/("""{"""),format.raw/*8.23*/("""
                    """),format.raw/*9.21*/("""font-family: Arial, sans-serif;
                    background-color: #f8f8f8;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    margin: 0;
                """),format.raw/*16.17*/("""}"""),format.raw/*16.18*/("""
                """),format.raw/*17.17*/(""".container """),format.raw/*17.28*/("""{"""),format.raw/*17.29*/("""
                    """),format.raw/*18.21*/("""width: 80%;
                    max-width: 600px;
                    padding: 40px;
                    background-color: #fff;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    text-align: center;
                """),format.raw/*25.17*/("""}"""),format.raw/*25.18*/("""
                """),format.raw/*26.17*/("""h1 """),format.raw/*26.20*/("""{"""),format.raw/*26.21*/("""
                    """),format.raw/*27.21*/("""color: #007bff;
                    margin-bottom: 20px;
                """),format.raw/*29.17*/("""}"""),format.raw/*29.18*/("""
                """),format.raw/*30.17*/(""".description """),format.raw/*30.30*/("""{"""),format.raw/*30.31*/("""
                    """),format.raw/*31.21*/("""margin-bottom: 20px;
                    color: #555;
                    font-size: 18px;
                """),format.raw/*34.17*/("""}"""),format.raw/*34.18*/("""
                """),format.raw/*35.17*/(""".buttons """),format.raw/*35.26*/("""{"""),format.raw/*35.27*/("""
                    """),format.raw/*36.21*/("""display: flex;
                    justify-content: center;
                    gap: 20px;
                """),format.raw/*39.17*/("""}"""),format.raw/*39.18*/("""
                """),format.raw/*40.17*/(""".button """),format.raw/*40.25*/("""{"""),format.raw/*40.26*/("""
                    """),format.raw/*41.21*/("""padding: 10px 20px;
                    font-size: 16px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
                """),format.raw/*47.17*/("""}"""),format.raw/*47.18*/("""
                """),format.raw/*48.17*/(""".button.login """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
                    """),format.raw/*49.21*/("""background-color: #007bff;
                    color: #fff;
                """),format.raw/*51.17*/("""}"""),format.raw/*51.18*/("""
                """),format.raw/*52.17*/(""".button.login:hover """),format.raw/*52.37*/("""{"""),format.raw/*52.38*/("""
                    """),format.raw/*53.21*/("""background-color: #0056b3;
                """),format.raw/*54.17*/("""}"""),format.raw/*54.18*/("""
                """),format.raw/*55.17*/(""".button.register """),format.raw/*55.34*/("""{"""),format.raw/*55.35*/("""
                    """),format.raw/*56.21*/("""background-color: #00cc00;
                    color: #fff;
                """),format.raw/*58.17*/("""}"""),format.raw/*58.18*/("""
                """),format.raw/*59.17*/(""".button.register:hover """),format.raw/*59.40*/("""{"""),format.raw/*59.41*/("""
                    """),format.raw/*60.21*/("""background-color: #009900;
                """),format.raw/*61.17*/("""}"""),format.raw/*61.18*/("""
        """),format.raw/*62.9*/("""</style>
    </head>
    <body>
        <div class="container">
            <h1>Добро пожаловать в To-Do List!</h1>
            <p class="description">Вы попали на стартовую страницу приложения. Здесь вы можете начать использование сервиса, выбрав одну из опций:</p>
            <div class="buttons">
                <form action=""""),_display_(/*69.32*/routes/*69.38*/.MainPageController.showLoginForm()),format.raw/*69.73*/("""" method="get">
                    <button class="button login" type="submit">Вход</button>
                </form>
                <form action=""""),_display_(/*72.32*/routes/*72.38*/.MainPageController.showRegistrationForm()),format.raw/*72.80*/("""" method="get">
                    <button class="button register" type="submit">Регистрация</button>
                </form>
            </div>
        </div>
    </body>
</html>
"""))
      }
    }
  }

  def render(message:String,messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(message)(messages)

  def f:((String) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (message) => (messages) => apply(message)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/welcomePage.scala.html
                  HASH: c0cfc5c3ad90b15332cd94c9765f8045031ef02b
                  MATRIX: 744->1|884->48|911->49|1045->156|1073->157|1121->178|1431->460|1460->461|1505->478|1544->489|1573->490|1622->511|1937->798|1966->799|2011->816|2042->819|2071->820|2120->841|2221->914|2250->915|2295->932|2336->945|2365->946|2414->967|2549->1074|2578->1075|2623->1092|2660->1101|2689->1102|2738->1123|2873->1230|2902->1231|2947->1248|2983->1256|3012->1257|3061->1278|3364->1553|3393->1554|3438->1571|3480->1585|3509->1586|3558->1607|3662->1683|3691->1684|3736->1701|3784->1721|3813->1722|3862->1743|3933->1786|3962->1787|4007->1804|4052->1821|4081->1822|4130->1843|4234->1919|4263->1920|4308->1937|4359->1960|4388->1961|4437->1982|4508->2025|4537->2026|4573->2035|4932->2367|4947->2373|5003->2408|5178->2556|5193->2562|5256->2604
                  LINES: 21->1|26->2|27->3|32->8|32->8|33->9|40->16|40->16|41->17|41->17|41->17|42->18|49->25|49->25|50->26|50->26|50->26|51->27|53->29|53->29|54->30|54->30|54->30|55->31|58->34|58->34|59->35|59->35|59->35|60->36|63->39|63->39|64->40|64->40|64->40|65->41|71->47|71->47|72->48|72->48|72->48|73->49|75->51|75->51|76->52|76->52|76->52|77->53|78->54|78->54|79->55|79->55|79->55|80->56|82->58|82->58|83->59|83->59|83->59|84->60|85->61|85->61|86->62|93->69|93->69|93->69|96->72|96->72|96->72
                  -- GENERATED --
              */
          