
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

object taskDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Models.Tasks.Task,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(task: Models.Tasks.Task):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
  <title>Task Details</title>
  <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
        """),format.raw/*13.9*/("""}"""),format.raw/*13.10*/("""
        """),format.raw/*14.9*/(""".task-details """),format.raw/*14.23*/("""{"""),format.raw/*14.24*/("""
            """),format.raw/*15.13*/("""border: 1px solid #dddddd;
            border-radius: 5px;
            padding: 20px;
            background-color: #f9f9f9;
        """),format.raw/*19.9*/("""}"""),format.raw/*19.10*/("""
        """),format.raw/*20.9*/(""".field-label """),format.raw/*20.22*/("""{"""),format.raw/*20.23*/("""
            """),format.raw/*21.13*/("""font-weight: bold;
        """),format.raw/*22.9*/("""}"""),format.raw/*22.10*/("""
        """),format.raw/*23.9*/(""".field-value """),format.raw/*23.22*/("""{"""),format.raw/*23.23*/("""
            """),format.raw/*24.13*/("""margin-bottom: 12px;
            word-break: break-word;
        """),format.raw/*26.9*/("""}"""),format.raw/*26.10*/("""
        """),format.raw/*27.9*/(""".back-button """),format.raw/*27.22*/("""{"""),format.raw/*27.23*/("""
            """),format.raw/*28.13*/("""margin-top: 12px;
        """),format.raw/*29.9*/("""}"""),format.raw/*29.10*/("""
        """),format.raw/*30.9*/(""".back-button a """),format.raw/*30.24*/("""{"""),format.raw/*30.25*/("""
            """),format.raw/*31.13*/("""background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 4px;
        """),format.raw/*36.9*/("""}"""),format.raw/*36.10*/("""
    """),format.raw/*37.5*/("""</style>
</head>
<body>
<h1>Детали задачи</h1>
<div class="task-details">
  <div class="field-label">ID:</div>
  <div class="field-value">"""),_display_(/*43.29*/task/*43.33*/.id),format.raw/*43.36*/("""</div>
  <div class="field-label">Заголовок:</div>
  <div class="field-value">"""),_display_(/*45.29*/task/*45.33*/.title),format.raw/*45.39*/("""</div>
  <div class="field-label">Описание:</div>
  <div class="field-value">"""),_display_(/*47.29*/task/*47.33*/.description),format.raw/*47.45*/("""</div>
  <div class="field-label">Дедлайн:</div>
  <div class="field-value">"""),_display_(/*49.29*/task/*49.33*/.dueDate),format.raw/*49.41*/("""</div>
  <div class="field-label">Дополнение:</div>
  <div class="field-value">"""),_display_(/*51.29*/task/*51.33*/.supplement.getOrElse("")),format.raw/*51.58*/("""</div>
</div>
<div class="back-button">
  <a href=""""),_display_(/*54.13*/routes/*54.19*/.ToDoListController.get_all()),format.raw/*54.48*/("""">Вернуться ко всем задачам</a>
</div>
</body>
</html>
"""))
      }
    }
  }

  def render(task:Models.Tasks.Task): play.twirl.api.HtmlFormat.Appendable = apply(task)

  def f:((Models.Tasks.Task) => play.twirl.api.HtmlFormat.Appendable) = (task) => apply(task)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/tasks/taskDetails.scala.html
                  HASH: 70ce0b104d03e5168f9c371bbc3124e19ecde511
                  MATRIX: 752->1|872->26|902->30|1017->118|1045->119|1086->133|1154->174|1183->175|1220->185|1251->188|1280->189|1322->203|1374->228|1403->229|1440->239|1482->253|1511->254|1553->268|1717->405|1746->406|1783->416|1824->429|1853->430|1895->444|1950->472|1979->473|2016->483|2057->496|2086->497|2128->511|2222->578|2251->579|2288->589|2329->602|2358->603|2400->617|2454->644|2483->645|2520->655|2563->670|2592->671|2634->685|2825->849|2854->850|2887->856|3059->1001|3072->1005|3096->1008|3204->1089|3217->1093|3244->1099|3351->1179|3364->1183|3397->1195|3503->1274|3516->1278|3545->1286|3654->1368|3667->1372|3713->1397|3795->1452|3810->1458|3860->1487
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|44->19|44->19|45->20|45->20|45->20|46->21|47->22|47->22|48->23|48->23|48->23|49->24|51->26|51->26|52->27|52->27|52->27|53->28|54->29|54->29|55->30|55->30|55->30|56->31|61->36|61->36|62->37|68->43|68->43|68->43|70->45|70->45|70->45|72->47|72->47|72->47|74->49|74->49|74->49|76->51|76->51|76->51|79->54|79->54|79->54
                  -- GENERATED --
              */
          