
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[Models.Tasks.Task],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(taskForm: Form[Models.Tasks.Task])(implicit messages: Messages, request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
 <title>Start Page</title>
 <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
            text-align: center;
            margin-top: 30px;
        """),format.raw/*15.9*/("""}"""),format.raw/*15.10*/("""
        """),format.raw/*16.9*/(""".intro-text """),format.raw/*16.21*/("""{"""),format.raw/*16.22*/("""
            """),format.raw/*17.13*/("""text-align: center;
            margin: 10px 0;
            color: #555;
            font-size: 18px;

        """),format.raw/*22.9*/("""}"""),format.raw/*22.10*/("""
        """),format.raw/*23.9*/("""form """),format.raw/*23.14*/("""{"""),format.raw/*23.15*/("""
            """),format.raw/*24.13*/("""width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""
        """),format.raw/*31.9*/("""label """),format.raw/*31.15*/("""{"""),format.raw/*31.16*/("""
            """),format.raw/*32.13*/("""display: block;
            margin-bottom: 8px;
            font-weight: bold;
        """),format.raw/*35.9*/("""}"""),format.raw/*35.10*/("""
        """),format.raw/*36.9*/("""input[type="text"], input[type="date"] """),format.raw/*36.48*/("""{"""),format.raw/*36.49*/("""
            """),format.raw/*37.13*/("""width: 90%;
            padding: 8px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            word-break: break-word;
            box-sizing: border-box;
        """),format.raw/*44.9*/("""}"""),format.raw/*44.10*/("""
        """),format.raw/*45.9*/("""input[type="submit"] """),format.raw/*45.30*/("""{"""),format.raw/*45.31*/("""
            """),format.raw/*46.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        """),format.raw/*53.9*/("""}"""),format.raw/*53.10*/("""
    """),format.raw/*54.5*/("""</style>
</head>
<body>
<h1>Добро пожаловать в To-Do List!</h1>
<p class="intro-text">Здесь вы можете организовать свои задачи и контролировать свои дела.</p>
"""),_display_(/*59.2*/helper/*59.8*/.form(action = helper.CSRF(routes.ToDoListController.save()))/*59.69*/ {_display_(Seq[Any](format.raw/*59.71*/("""

"""),_display_(/*61.2*/helper/*61.8*/.inputText(taskForm("Задача"))),format.raw/*61.38*/("""

"""),_display_(/*63.2*/helper/*63.8*/.inputText(taskForm("Описание"))),format.raw/*63.40*/("""

"""),_display_(/*65.2*/helper/*65.8*/.inputDate(taskForm("Дедлайн"))),format.raw/*65.39*/("""

"""),_display_(/*67.2*/helper/*67.8*/.inputText(taskForm("Дополнение"))),format.raw/*67.42*/("""

"""),format.raw/*69.1*/("""<input type="submit" value="Сохранить задачу">
""")))}),format.raw/*70.2*/("""
"""),format.raw/*71.1*/("""</body>
</html>
"""))
      }
    }
  }

  def render(taskForm:Form[Models.Tasks.Task],messages:Messages,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(taskForm)(messages,request)

  def f:((Form[Models.Tasks.Task]) => (Messages,RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (taskForm) => (messages,request) => apply(taskForm)(messages,request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/users/index.scala.html
                  HASH: a9b8174337eddb55417db27f20c6247f72db6509
                  MATRIX: 775->1|957->90|984->91|1090->170|1118->171|1158->184|1225->224|1254->225|1290->234|1321->237|1350->238|1391->251|1504->337|1533->338|1569->347|1609->359|1638->360|1679->373|1817->484|1846->485|1882->494|1915->499|1944->500|1985->513|2196->697|2225->698|2261->707|2295->713|2324->714|2365->727|2479->814|2508->815|2544->824|2611->863|2640->864|2681->877|2927->1096|2956->1097|2992->1106|3041->1127|3070->1128|3111->1141|3342->1345|3371->1346|3403->1351|3589->1511|3603->1517|3673->1578|3713->1580|3742->1583|3756->1589|3807->1619|3836->1622|3850->1628|3903->1660|3932->1663|3946->1669|3998->1700|4027->1703|4041->1709|4096->1743|4125->1745|4203->1793|4231->1794
                  LINES: 21->1|26->2|27->3|32->8|32->8|33->9|34->10|34->10|35->11|35->11|35->11|36->12|39->15|39->15|40->16|40->16|40->16|41->17|46->22|46->22|47->23|47->23|47->23|48->24|54->30|54->30|55->31|55->31|55->31|56->32|59->35|59->35|60->36|60->36|60->36|61->37|68->44|68->44|69->45|69->45|69->45|70->46|77->53|77->53|78->54|83->59|83->59|83->59|83->59|85->61|85->61|85->61|87->63|87->63|87->63|89->65|89->65|89->65|91->67|91->67|91->67|93->69|94->70|95->71
                  -- GENERATED --
              */
          