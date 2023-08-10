
package views.html.tasks

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

object createTask extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Form[Models.Tasks.Task],Messages,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(taskForm: Form[Models.Tasks.Task])(implicit messages: Messages, request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.90*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
    <title>Create Task</title>
    <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
        """),format.raw/*13.9*/("""}"""),format.raw/*13.10*/("""
        """),format.raw/*14.9*/("""form """),format.raw/*14.14*/("""{"""),format.raw/*14.15*/("""
            """),format.raw/*15.13*/("""width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        """),format.raw/*21.9*/("""}"""),format.raw/*21.10*/("""
        """),format.raw/*22.9*/("""label """),format.raw/*22.15*/("""{"""),format.raw/*22.16*/("""
            """),format.raw/*23.13*/("""display: block;
            margin-bottom: 8px;
            font-weight: bold;
        """),format.raw/*26.9*/("""}"""),format.raw/*26.10*/("""
        """),format.raw/*27.9*/("""input[type="text"], input[type="date"] """),format.raw/*27.48*/("""{"""),format.raw/*27.49*/("""
            """),format.raw/*28.13*/("""width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-left: -25px;
            width: 150px
            word-break: break-word;
        """),format.raw/*36.9*/("""}"""),format.raw/*36.10*/("""
        """),format.raw/*37.9*/("""input[type="submit"] """),format.raw/*37.30*/("""{"""),format.raw/*37.31*/("""
            """),format.raw/*38.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
        """),format.raw/*44.9*/("""}"""),format.raw/*44.10*/("""
    """),format.raw/*45.5*/("""</style>
</head>
<body>
<h1>Создание задачи</h1>
"""),_display_(/*49.2*/helper/*49.8*/.form(action = helper.CSRF(routes.ToDoListController.save()))/*49.69*/ {_display_(Seq[Any](format.raw/*49.71*/("""



"""),_display_(/*53.2*/helper/*53.8*/.inputText(taskForm("Задача"))),format.raw/*53.38*/("""


"""),_display_(/*56.2*/helper/*56.8*/.inputText(taskForm("Описание"))),format.raw/*56.40*/("""


"""),_display_(/*59.2*/helper/*59.8*/.inputDate(taskForm("Дедлайн"))),format.raw/*59.39*/("""


"""),_display_(/*62.2*/helper/*62.8*/.inputText(taskForm("Дополнение"))),format.raw/*62.42*/("""

"""),format.raw/*64.1*/("""<input type="submit" value="Сохранить задачу">
""")))}),format.raw/*65.2*/("""
"""),format.raw/*66.1*/("""</body>
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
                  SOURCE: app/views/tasks/createTask.scala.html
                  HASH: a34a56034b16e9499ec8a38b70ff0688ea1af6ca
                  MATRIX: 780->1|963->89|993->93|1111->184|1139->185|1180->199|1248->240|1277->241|1314->251|1345->254|1374->255|1416->269|1468->294|1497->295|1534->305|1567->310|1596->311|1638->325|1855->515|1884->516|1921->526|1955->532|1984->533|2026->547|2143->637|2172->638|2209->648|2276->687|2305->688|2347->702|2623->951|2652->952|2689->962|2738->983|2767->984|2809->998|3021->1183|3050->1184|3083->1190|3163->1244|3177->1250|3247->1311|3287->1313|3322->1322|3336->1328|3387->1358|3420->1365|3434->1371|3487->1403|3520->1410|3534->1416|3586->1447|3619->1454|3633->1460|3688->1494|3719->1498|3798->1547|3827->1549
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|46->21|46->21|47->22|47->22|47->22|48->23|51->26|51->26|52->27|52->27|52->27|53->28|61->36|61->36|62->37|62->37|62->37|63->38|69->44|69->44|70->45|74->49|74->49|74->49|74->49|78->53|78->53|78->53|81->56|81->56|81->56|84->59|84->59|84->59|87->62|87->62|87->62|89->64|90->65|91->66
                  -- GENERATED --
              */
          