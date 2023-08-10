
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

object doneTasks extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Seq[Models.Tasks.Task],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(doneTasks: Seq[Models.Tasks.Task]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.37*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
  <title>Done Tasks</title>
  <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
        """),format.raw/*13.9*/("""}"""),format.raw/*13.10*/("""
        """),format.raw/*14.9*/("""table """),format.raw/*14.15*/("""{"""),format.raw/*14.16*/("""
            """),format.raw/*15.13*/("""border-collapse: collapse;
            width: 100%;
        """),format.raw/*17.9*/("""}"""),format.raw/*17.10*/("""
        """),format.raw/*18.9*/("""th, td """),format.raw/*18.16*/("""{"""),format.raw/*18.17*/("""
            """),format.raw/*19.13*/("""border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            width: 150px;
            word-break: break-word;
        """),format.raw/*24.9*/("""}"""),format.raw/*24.10*/("""
        """),format.raw/*25.9*/("""tr:nth-child(even) """),format.raw/*25.28*/("""{"""),format.raw/*25.29*/("""
            """),format.raw/*26.13*/("""background-color: #f2f2f2;
        """),format.raw/*27.9*/("""}"""),format.raw/*27.10*/("""
        """),format.raw/*28.9*/(""".back-button """),format.raw/*28.22*/("""{"""),format.raw/*28.23*/("""
            """),format.raw/*29.13*/("""margin-top: 12px;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""
        """),format.raw/*31.9*/(""".back-button a, .clear-button input[type="submit"] """),format.raw/*31.60*/("""{"""),format.raw/*31.61*/("""
            """),format.raw/*32.13*/("""background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        """),format.raw/*39.9*/("""}"""),format.raw/*39.10*/("""
        """),format.raw/*40.9*/(""".back-button a:hover, .clear-button input[type="submit"]:hover """),format.raw/*40.72*/("""{"""),format.raw/*40.73*/("""
            """),format.raw/*41.13*/("""background-color: #0056b3;
        """),format.raw/*42.9*/("""}"""),format.raw/*42.10*/("""
        """),format.raw/*43.9*/(""".button-container """),format.raw/*43.27*/("""{"""),format.raw/*43.28*/("""
            """),format.raw/*44.13*/("""margin-top: 12px;
            display: flex;
            gap: 10px;
        """),format.raw/*47.9*/("""}"""),format.raw/*47.10*/("""
        """),format.raw/*48.9*/(""".clear-button input[type="submit"] """),format.raw/*48.44*/("""{"""),format.raw/*48.45*/("""
            """),format.raw/*49.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        """),format.raw/*56.9*/("""}"""),format.raw/*56.10*/("""
        """),format.raw/*57.9*/(""".clear-button input[type="submit"]:hover """),format.raw/*57.50*/("""{"""),format.raw/*57.51*/("""
            """),format.raw/*58.13*/("""background-color: #0056b3;
        """),format.raw/*59.9*/("""}"""),format.raw/*59.10*/("""
    """),format.raw/*60.5*/("""</style>
</head>
<body>
<h1>Решенные задачи</h1>
<table>
  <tr>
    <th>Заголовок</th>
    <th>Описание</th>
    <th>Дедлайн</th>
    <th>Дополнение</th>
  </tr>
  """),_display_(/*71.4*/for(task <- doneTasks) yield /*71.26*/ {_display_(Seq[Any](format.raw/*71.28*/("""
  """),format.raw/*72.3*/("""<tr>

    <td>"""),_display_(/*74.10*/task/*74.14*/.title),format.raw/*74.20*/("""</td>
    <td>"""),_display_(/*75.10*/task/*75.14*/.description),format.raw/*75.26*/("""</td>
    <td>"""),_display_(/*76.10*/task/*76.14*/.dueDate),format.raw/*76.22*/("""</td>
    <td>"""),_display_(/*77.10*/task/*77.14*/.supplement.getOrElse("")),format.raw/*77.39*/("""</td>
  </tr>
  """)))}),format.raw/*79.4*/("""
"""),format.raw/*80.1*/("""</table>
<div class="button-container">
  <div class="clear-button">
    <form action=""""),_display_(/*83.20*/routes/*83.26*/.ToDoListController.clear("clearDoneTasks")),format.raw/*83.69*/("""" method="GET">
    <input type="submit" value="Очистить список задач">
    </form>
  </div>
  <div class="back-button">
    <a href=""""),_display_(/*88.15*/routes/*88.21*/.ToDoListController.get_all()),format.raw/*88.50*/("""">Вернуться ко всем задачам</a>
  </div>
</div>
</body>
</html>
"""))
      }
    }
  }

  def render(doneTasks:Seq[Models.Tasks.Task]): play.twirl.api.HtmlFormat.Appendable = apply(doneTasks)

  def f:((Seq[Models.Tasks.Task]) => play.twirl.api.HtmlFormat.Appendable) = (doneTasks) => apply(doneTasks)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/tasks/doneTasks.scala.html
                  HASH: 78322991c4dec8c97ae59b4797d6517c40e35bb7
                  MATRIX: 755->1|885->36|915->40|1028->126|1056->127|1097->141|1165->182|1194->183|1231->193|1262->196|1291->197|1333->211|1385->236|1414->237|1451->247|1485->253|1514->254|1556->268|1645->330|1674->331|1711->341|1746->348|1775->349|1817->363|2002->521|2031->522|2068->532|2115->551|2144->552|2186->566|2249->602|2278->603|2315->613|2356->626|2385->627|2427->641|2481->668|2510->669|2547->679|2626->730|2655->731|2697->745|2948->969|2977->970|3014->980|3105->1043|3134->1044|3176->1058|3239->1094|3268->1095|3305->1105|3351->1123|3380->1124|3422->1138|3528->1217|3557->1218|3594->1228|3657->1263|3686->1264|3728->1278|3970->1493|3999->1494|4036->1504|4105->1545|4134->1546|4176->1560|4239->1596|4268->1597|4301->1603|4503->1779|4541->1801|4581->1803|4612->1807|4656->1824|4669->1828|4696->1834|4739->1850|4752->1854|4785->1866|4828->1882|4841->1886|4870->1894|4913->1910|4926->1914|4972->1939|5021->1958|5050->1960|5168->2051|5183->2057|5247->2100|5414->2240|5429->2246|5479->2275
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|42->17|42->17|43->18|43->18|43->18|44->19|49->24|49->24|50->25|50->25|50->25|51->26|52->27|52->27|53->28|53->28|53->28|54->29|55->30|55->30|56->31|56->31|56->31|57->32|64->39|64->39|65->40|65->40|65->40|66->41|67->42|67->42|68->43|68->43|68->43|69->44|72->47|72->47|73->48|73->48|73->48|74->49|81->56|81->56|82->57|82->57|82->57|83->58|84->59|84->59|85->60|96->71|96->71|96->71|97->72|99->74|99->74|99->74|100->75|100->75|100->75|101->76|101->76|101->76|102->77|102->77|102->77|104->79|105->80|108->83|108->83|108->83|113->88|113->88|113->88
                  -- GENERATED --
              */
          