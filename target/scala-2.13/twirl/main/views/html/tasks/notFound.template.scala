
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

object notFound extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Messages,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String)(implicit messages: Messages):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.48*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
<head>
  <title>Task Not Found</title>
  <style>
        body """),format.raw/*8.14*/("""{"""),format.raw/*8.15*/("""
            """),format.raw/*9.13*/("""font-family: Arial, sans-serif;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
        """),format.raw/*11.9*/("""h1 """),format.raw/*11.12*/("""{"""),format.raw/*11.13*/("""
            """),format.raw/*12.13*/("""color: #007bff;
        """),format.raw/*13.9*/("""}"""),format.raw/*13.10*/("""
        """),format.raw/*14.9*/("""p """),format.raw/*14.11*/("""{"""),format.raw/*14.12*/("""
            """),format.raw/*15.13*/("""margin-bottom: 16px;
        """),format.raw/*16.9*/("""}"""),format.raw/*16.10*/("""
        """),format.raw/*17.9*/("""input[type="submit"] """),format.raw/*17.30*/("""{"""),format.raw/*17.31*/("""
            """),format.raw/*18.13*/("""background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 16px;
            border-radius: 4px;
            cursor: pointer;
        """),format.raw/*24.9*/("""}"""),format.raw/*24.10*/("""
    """),format.raw/*25.5*/("""</style>
</head>
<body>
<h1>Ошибка</h1>
<p>"""),_display_(/*29.5*/message),format.raw/*29.12*/("""</p>
<form action=""""),_display_(/*30.16*/routes/*30.22*/.ToDoListController.get_all()),format.raw/*30.51*/("""" method="GET">
  <input type="submit" value="Вернуться к списку задач">
</form>
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
                  SOURCE: app/views/tasks/notFound.scala.html
                  HASH: be39cbfc7ae0687e78c3e69639ab72e16fc4cac1
                  MATRIX: 747->1|888->47|918->51|1035->141|1063->142|1104->156|1172->197|1201->198|1238->208|1269->211|1298->212|1340->226|1392->251|1421->252|1458->262|1488->264|1517->265|1559->279|1616->309|1645->310|1682->320|1731->341|1760->342|1802->356|2014->541|2043->542|2076->548|2150->596|2178->603|2226->624|2241->630|2291->659
                  LINES: 21->1|26->1|28->3|33->8|33->8|34->9|35->10|35->10|36->11|36->11|36->11|37->12|38->13|38->13|39->14|39->14|39->14|40->15|41->16|41->16|42->17|42->17|42->17|43->18|49->24|49->24|50->25|54->29|54->29|55->30|55->30|55->30
                  -- GENERATED --
              */
          