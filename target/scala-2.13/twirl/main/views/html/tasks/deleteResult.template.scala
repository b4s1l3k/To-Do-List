
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

object deleteResult extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Models.Tasks.Task,Seq[Models.Tasks.Task],Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(deletedTask: Models.Tasks.Task, remainingTasks: Seq[Models.Tasks.Task])(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.103*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
  <title>Delete Task</title>
  <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
        """),format.raw/*13.9*/("""}"""),format.raw/*13.10*/("""
        """),format.raw/*14.9*/("""p """),format.raw/*14.11*/("""{"""),format.raw/*14.12*/("""
            """),format.raw/*15.13*/("""margin-bottom: 8px;
        """),format.raw/*16.9*/("""}"""),format.raw/*16.10*/("""
        """),format.raw/*17.9*/("""table """),format.raw/*17.15*/("""{"""),format.raw/*17.16*/("""
            """),format.raw/*18.13*/("""border-collapse: collapse;
            width: 100%;
        """),format.raw/*20.9*/("""}"""),format.raw/*20.10*/("""
        """),format.raw/*21.9*/("""th, td """),format.raw/*21.16*/("""{"""),format.raw/*21.17*/("""
            """),format.raw/*22.13*/("""border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            width: 150px;
            word-break: break-word;
        """),format.raw/*27.9*/("""}"""),format.raw/*27.10*/("""
        """),format.raw/*28.9*/("""tr:nth-child(even) """),format.raw/*28.28*/("""{"""),format.raw/*28.29*/("""
            """),format.raw/*29.13*/("""background-color: #f2f2f2;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""
        """),format.raw/*31.9*/("""input[type="submit"] """),format.raw/*31.30*/("""{"""),format.raw/*31.31*/("""
            """),format.raw/*32.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
        """),format.raw/*38.9*/("""}"""),format.raw/*38.10*/("""
    """),format.raw/*39.5*/("""</style>
</head>
<body>
<h1>Удаление задачи</h1>
<p>Из списка задач была удалена задача:</p>
<table>
  <tr>
    <th>ID</th>
    <th>Заголовок</th>
    <th>Описание</th>
    <th>Дедлайн</th>
    <th>Дополнение</th>
  </tr>
  <tr>
    <td>"""),_display_(/*53.10*/deletedTask/*53.21*/.id),format.raw/*53.24*/("""</td>
    <td>"""),_display_(/*54.10*/deletedTask/*54.21*/.title),format.raw/*54.27*/("""</td>
    <td>"""),_display_(/*55.10*/deletedTask/*55.21*/.description),format.raw/*55.33*/("""</td>
    <td>"""),_display_(/*56.10*/deletedTask/*56.21*/.dueDate),format.raw/*56.29*/("""</td>
    <td>"""),_display_(/*57.10*/deletedTask/*57.21*/.supplement.getOrElse("")),format.raw/*57.46*/("""</td>
  </tr>
</table>
<p>В списке задач осталось:</p>
<table>
  <tr>
    <th>ID</th>
    <th>Заголовок</th>
    <th>Описание</th>
    <th>Дедлайн</th>
    <th>Дополнение</th>
  </tr>
  """),_display_(/*69.4*/for(task <- remainingTasks) yield /*69.31*/ {_display_(Seq[Any](format.raw/*69.33*/("""
  """),format.raw/*70.3*/("""<tr>
    <td>"""),_display_(/*71.10*/task/*71.14*/.id),format.raw/*71.17*/("""</td>
    <td>"""),_display_(/*72.10*/task/*72.14*/.title),format.raw/*72.20*/("""</td>
    <td>"""),_display_(/*73.10*/task/*73.14*/.description),format.raw/*73.26*/("""</td>
    <td>"""),_display_(/*74.10*/task/*74.14*/.dueDate),format.raw/*74.22*/("""</td>
    <td>"""),_display_(/*75.10*/task/*75.14*/.supplement.getOrElse("")),format.raw/*75.39*/("""</td>
  </tr>
  """)))}),format.raw/*77.4*/("""
"""),format.raw/*78.1*/("""</table>
<form action=""""),_display_(/*79.16*/routes/*79.22*/.ToDoListController.get_all()),format.raw/*79.51*/("""" method="GET">
  <input type="submit" value="Список задач">
</form>

</body>
</html>
"""))
      }
    }
  }

  def render(deletedTask:Models.Tasks.Task,remainingTasks:Seq[Models.Tasks.Task],messages:Messages): play.twirl.api.HtmlFormat.Appendable = apply(deletedTask,remainingTasks)(messages)

  def f:((Models.Tasks.Task,Seq[Models.Tasks.Task]) => (Messages) => play.twirl.api.HtmlFormat.Appendable) = (deletedTask,remainingTasks) => (messages) => apply(deletedTask,remainingTasks)(messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/tasks/deleteResult.scala.html
                  HASH: 450c0debc690a27fab515efa97f3385dcc3c2612
                  MATRIX: 785->1|982->102|1012->106|1126->193|1154->194|1195->208|1263->249|1292->250|1329->260|1360->263|1389->264|1431->278|1483->303|1512->304|1549->314|1579->316|1608->317|1650->331|1706->360|1735->361|1772->371|1806->377|1835->378|1877->392|1966->454|1995->455|2032->465|2067->472|2096->473|2138->487|2323->645|2352->646|2389->656|2436->675|2465->676|2507->690|2570->726|2599->727|2636->737|2685->758|2714->759|2756->773|2968->958|2997->959|3030->965|3309->1217|3329->1228|3353->1231|3396->1247|3416->1258|3443->1264|3486->1280|3506->1291|3539->1303|3582->1319|3602->1330|3631->1338|3674->1354|3694->1365|3740->1390|3965->1589|4008->1616|4048->1618|4079->1622|4121->1637|4134->1641|4158->1644|4201->1660|4214->1664|4241->1670|4284->1686|4297->1690|4330->1702|4373->1718|4386->1722|4415->1730|4458->1746|4471->1750|4517->1775|4566->1794|4595->1796|4647->1821|4662->1827|4712->1856
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|41->16|41->16|42->17|42->17|42->17|43->18|45->20|45->20|46->21|46->21|46->21|47->22|52->27|52->27|53->28|53->28|53->28|54->29|55->30|55->30|56->31|56->31|56->31|57->32|63->38|63->38|64->39|78->53|78->53|78->53|79->54|79->54|79->54|80->55|80->55|80->55|81->56|81->56|81->56|82->57|82->57|82->57|94->69|94->69|94->69|95->70|96->71|96->71|96->71|97->72|97->72|97->72|98->73|98->73|98->73|99->74|99->74|99->74|100->75|100->75|100->75|102->77|103->78|104->79|104->79|104->79
                  -- GENERATED --
              */
          