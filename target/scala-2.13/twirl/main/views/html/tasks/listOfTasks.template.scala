
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

object listOfTasks extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Seq[Models.Tasks.Task],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(tasks: Seq[Models.Tasks.Task]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.33*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>To-Do List</title>
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
        """),format.raw/*28.9*/(""".add-edit-details-buttons """),format.raw/*28.35*/("""{"""),format.raw/*28.36*/("""
            """),format.raw/*29.13*/("""margin-top: 12px;
        """),format.raw/*30.9*/("""}"""),format.raw/*30.10*/("""
        """),format.raw/*31.9*/(""".add-edit-details-buttons input[type="submit"] """),format.raw/*31.56*/("""{"""),format.raw/*31.57*/("""
            """),format.raw/*32.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            border-width: 1px;
            border-style: solid;
        """),format.raw/*41.9*/("""}"""),format.raw/*41.10*/("""
        """),format.raw/*42.9*/(""".add-edit-details-buttons input[type="submit"]:hover """),format.raw/*42.62*/("""{"""),format.raw/*42.63*/("""
            """),format.raw/*43.13*/("""border-color: #0056b3;
        """),format.raw/*44.9*/("""}"""),format.raw/*44.10*/("""
        """),format.raw/*45.9*/(""".buttons-container """),format.raw/*45.28*/("""{"""),format.raw/*45.29*/("""
            """),format.raw/*46.13*/("""margin-top: 12px;
        """),format.raw/*47.9*/("""}"""),format.raw/*47.10*/("""
        """),format.raw/*48.9*/(""".add-button,
        .clear-button,
        .deleted-tasks-button,
        .logout-button """),format.raw/*51.24*/("""{"""),format.raw/*51.25*/("""
            """),format.raw/*52.13*/("""display: inline-block;
            margin-right: 10px;
        """),format.raw/*54.9*/("""}"""),format.raw/*54.10*/("""
        """),format.raw/*55.9*/(""".add-button input[type="submit"],
        .clear-button input[type="submit"],
        .deleted-tasks-button input[type="submit"],
        .logout-button input[type="submit"] """),format.raw/*58.45*/("""{"""),format.raw/*58.46*/("""
            """),format.raw/*59.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
        """),format.raw/*65.9*/("""}"""),format.raw/*65.10*/("""
        """),format.raw/*66.9*/(""".add-button input[type="submit"]:hover,
        .clear-button input[type="submit"]:hover,
        .deleted-tasks-button input[type="submit"]:hover,
        .logout-button input[type="submit"]:hover """),format.raw/*69.51*/("""{"""),format.raw/*69.52*/("""
            """),format.raw/*70.13*/("""background-color: #0056b3;
        """),format.raw/*71.9*/("""}"""),format.raw/*71.10*/("""
        """),format.raw/*72.9*/(""".deleted-tasks-button input[type="submit"] """),format.raw/*72.52*/("""{"""),format.raw/*72.53*/("""
            """),format.raw/*73.13*/("""background-color: #86e686;
        """),format.raw/*74.9*/("""}"""),format.raw/*74.10*/("""
        """),format.raw/*75.9*/(""".deleted-tasks-button input[type="submit"]:hover """),format.raw/*75.58*/("""{"""),format.raw/*75.59*/("""
            """),format.raw/*76.13*/("""background-color: #6edc6e;
        """),format.raw/*77.9*/("""}"""),format.raw/*77.10*/("""
        """),format.raw/*78.9*/(""".add-edit-details-buttons form:nth-child(1) input[type="submit"] """),format.raw/*78.74*/("""{"""),format.raw/*78.75*/("""
            """),format.raw/*79.13*/("""background-color: #007bff;
        """),format.raw/*80.9*/("""}"""),format.raw/*80.10*/("""
        """),format.raw/*81.9*/(""".add-edit-details-buttons form:nth-child(1) input[type="submit"]:hover """),format.raw/*81.80*/("""{"""),format.raw/*81.81*/("""
            """),format.raw/*82.13*/("""background-color: #0056b3;
        """),format.raw/*83.9*/("""}"""),format.raw/*83.10*/("""
        """),format.raw/*84.9*/(""".add-edit-details-buttons form:nth-child(2) input[type="submit"] """),format.raw/*84.74*/("""{"""),format.raw/*84.75*/("""
            """),format.raw/*85.13*/("""background-color: #007bff;
        """),format.raw/*86.9*/("""}"""),format.raw/*86.10*/("""
        """),format.raw/*87.9*/(""".add-edit-details-buttons form:nth-child(2) input[type="submit"]:hover """),format.raw/*87.80*/("""{"""),format.raw/*87.81*/("""
            """),format.raw/*88.13*/("""background-color: #0056b3;
        """),format.raw/*89.9*/("""}"""),format.raw/*89.10*/("""
    """),format.raw/*90.5*/("""</style>
    </head>
    <body>
        <h1>To-Do List</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Заголовок</th>
                <th>Описание</th>
                <th>Дедлайн</th>
                <th>Дополнение</th>
                <th>Действия</th>
            </tr>
            """),_display_(/*103.14*/for(task <- tasks) yield /*103.32*/ {_display_(Seq[Any](format.raw/*103.34*/("""
                """),format.raw/*104.17*/("""<tr>
                    <td>"""),_display_(/*105.26*/task/*105.30*/.id),format.raw/*105.33*/("""</td>
                    <td>"""),_display_(/*106.26*/task/*106.30*/.title),format.raw/*106.36*/("""</td>
                    <td>"""),_display_(/*107.26*/task/*107.30*/.description),format.raw/*107.42*/("""</td>
                    <td>"""),_display_(/*108.26*/task/*108.30*/.dueDate),format.raw/*108.38*/("""</td>
                    <td>"""),_display_(/*109.26*/task/*109.30*/.supplement.getOrElse("")),format.raw/*109.55*/("""</td>
                    <td class="add-edit-details-buttons">
                        <form action=""""),_display_(/*111.40*/routes/*111.46*/.ToDoListController.delete_one(task.id)),format.raw/*111.85*/("""" method="GET">
                            <input type="submit" value="Решена">
                        </form>
                        <form action=""""),_display_(/*114.40*/routes/*114.46*/.ToDoListController.get_task(task.id)),format.raw/*114.83*/("""" method="GET">
                            <input type="submit" value="Детали">
                        </form>
                        <form action=""""),_display_(/*117.40*/routes/*117.46*/.ToDoListController.edit(task.id)),format.raw/*117.79*/("""" method="GET">
                            <input type="submit" value="Редактировать">
                        </form>
                    </td>
                </tr>
            """)))}),format.raw/*122.14*/("""
            """),format.raw/*123.13*/("""</table>
        <div class="buttons-container">
            <div class="add-button">
                <form action=""""),_display_(/*126.32*/routes/*126.38*/.ToDoListController.create()),format.raw/*126.66*/("""" method="GET">
                    <input type="submit" value="Добавить задачу">
                </form>
            </div>
            <div class="clear-button">
                <form action=""""),_display_(/*131.32*/routes/*131.38*/.ToDoListController.clear("clearTasks")),format.raw/*131.77*/("""" method="GET">
                    <input type="submit" value="Очистить список задач">
                </form>
            </div>
            <div class="deleted-tasks-button">
                <form action=""""),_display_(/*136.32*/routes/*136.38*/.ToDoListController.deleted()),format.raw/*136.67*/("""" method="GET">
                    <input type="submit" value="Решенные задачи">
                </form>
            </div>
            <div class="logout-button">
                <form action=""""),_display_(/*141.32*/routes/*141.38*/.MainPageController.logout()),format.raw/*141.66*/("""" method="GET">
                    <input type="submit" value="Выход">
                </form>
            </div>
        </div>
    </body>
</html>
"""))
      }
    }
  }

  def render(tasks:Seq[Models.Tasks.Task]): play.twirl.api.HtmlFormat.Appendable = apply(tasks)

  def f:((Seq[Models.Tasks.Task]) => play.twirl.api.HtmlFormat.Appendable) = (tasks) => apply(tasks)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/tasks/listOfTasks.scala.html
                  HASH: 029f60b2f0cc68d606ccd938d1c5f98ec91f036c
                  MATRIX: 757->1|883->32|913->36|1042->138|1070->139|1111->153|1179->194|1208->195|1245->205|1276->208|1305->209|1347->223|1399->248|1428->249|1465->259|1499->265|1528->266|1570->280|1659->342|1688->343|1725->353|1760->360|1789->361|1831->375|2016->533|2045->534|2082->544|2129->563|2158->564|2200->578|2263->614|2292->615|2329->625|2383->651|2412->652|2454->666|2508->693|2537->694|2574->704|2649->751|2678->752|2720->766|3029->1048|3058->1049|3095->1059|3176->1112|3205->1113|3247->1127|3306->1159|3335->1160|3372->1170|3419->1189|3448->1190|3490->1204|3544->1231|3573->1232|3610->1242|3731->1335|3760->1336|3802->1350|3894->1415|3923->1416|3960->1426|4165->1603|4194->1604|4236->1618|4448->1803|4477->1804|4514->1814|4743->2015|4772->2016|4814->2030|4877->2066|4906->2067|4943->2077|5014->2120|5043->2121|5085->2135|5148->2171|5177->2172|5214->2182|5291->2231|5320->2232|5362->2246|5425->2282|5454->2283|5491->2293|5584->2358|5613->2359|5655->2373|5718->2409|5747->2410|5784->2420|5883->2491|5912->2492|5954->2506|6017->2542|6046->2543|6083->2553|6176->2618|6205->2619|6247->2633|6310->2669|6339->2670|6376->2680|6475->2751|6504->2752|6546->2766|6609->2802|6638->2803|6671->2809|7036->3146|7071->3164|7112->3166|7159->3184|7218->3215|7232->3219|7257->3222|7317->3254|7331->3258|7359->3264|7419->3296|7433->3300|7467->3312|7527->3344|7541->3348|7571->3356|7631->3388|7645->3392|7692->3417|7825->3522|7841->3528|7902->3567|8085->3722|8101->3728|8160->3765|8343->3920|8359->3926|8414->3959|8632->4145|8675->4159|8823->4279|8839->4285|8889->4313|9117->4513|9133->4519|9194->4558|9436->4772|9452->4778|9503->4807|9732->5008|9748->5014|9798->5042
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|42->17|42->17|43->18|43->18|43->18|44->19|49->24|49->24|50->25|50->25|50->25|51->26|52->27|52->27|53->28|53->28|53->28|54->29|55->30|55->30|56->31|56->31|56->31|57->32|66->41|66->41|67->42|67->42|67->42|68->43|69->44|69->44|70->45|70->45|70->45|71->46|72->47|72->47|73->48|76->51|76->51|77->52|79->54|79->54|80->55|83->58|83->58|84->59|90->65|90->65|91->66|94->69|94->69|95->70|96->71|96->71|97->72|97->72|97->72|98->73|99->74|99->74|100->75|100->75|100->75|101->76|102->77|102->77|103->78|103->78|103->78|104->79|105->80|105->80|106->81|106->81|106->81|107->82|108->83|108->83|109->84|109->84|109->84|110->85|111->86|111->86|112->87|112->87|112->87|113->88|114->89|114->89|115->90|128->103|128->103|128->103|129->104|130->105|130->105|130->105|131->106|131->106|131->106|132->107|132->107|132->107|133->108|133->108|133->108|134->109|134->109|134->109|136->111|136->111|136->111|139->114|139->114|139->114|142->117|142->117|142->117|147->122|148->123|151->126|151->126|151->126|156->131|156->131|156->131|161->136|161->136|161->136|166->141|166->141|166->141
                  -- GENERATED --
              */
          