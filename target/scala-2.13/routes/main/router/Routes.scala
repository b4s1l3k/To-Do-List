// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:3
  MainPageController_0: controllers.MainPageController,
  // @LINE:5
  ToDoListController_1: controllers.ToDoListController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:3
    MainPageController_0: controllers.MainPageController,
    // @LINE:5
    ToDoListController_1: controllers.ToDoListController
  ) = this(errorHandler, MainPageController_0, ToDoListController_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, MainPageController_0, ToDoListController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.MainPageController.weclomePage()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """main""", """controllers.MainPageController.firstTask()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """main""", """controllers.ToDoListController.save()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.MainPageController.showRegistrationForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """register""", """controllers.MainPageController.saveUser()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.MainPageController.showLoginForm()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.MainPageController.login()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """logout""", """controllers.MainPageController.logout()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """debug2""", """controllers.MainPageController.debugUsers()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks""", """controllers.ToDoListController.get_all()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/create""", """controllers.ToDoListController.create()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/""" + "$" + """id<[^/]+>""", """controllers.ToDoListController.get_task(id:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/edit/""" + "$" + """id<[^/]+>""", """controllers.ToDoListController.edit(id:Int)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/edit""", """controllers.ToDoListController.update()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/create""", """controllers.ToDoListController.save()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasks/delete/""" + "$" + """id<[^/]+>""", """controllers.ToDoListController.delete_one(id:Int)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleted""", """controllers.ToDoListController.deleted()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clear/""" + "$" + """tableName<[^/]+>""", """controllers.ToDoListController.clear(tableName:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """debug1""", """controllers.ToDoListController.debugToDoList()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:3
  private[this] lazy val controllers_MainPageController_weclomePage0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_MainPageController_weclomePage0_invoker = createInvoker(
    MainPageController_0.weclomePage(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "weclomePage",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_MainPageController_firstTask1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("main")))
  )
  private[this] lazy val controllers_MainPageController_firstTask1_invoker = createInvoker(
    MainPageController_0.firstTask(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "firstTask",
      Nil,
      "GET",
      this.prefix + """main""",
      """""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_ToDoListController_save2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("main")))
  )
  private[this] lazy val controllers_ToDoListController_save2_invoker = createInvoker(
    ToDoListController_1.save(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "save",
      Nil,
      "POST",
      this.prefix + """main""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_MainPageController_showRegistrationForm3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_MainPageController_showRegistrationForm3_invoker = createInvoker(
    MainPageController_0.showRegistrationForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "showRegistrationForm",
      Nil,
      "GET",
      this.prefix + """register""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_MainPageController_saveUser4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("register")))
  )
  private[this] lazy val controllers_MainPageController_saveUser4_invoker = createInvoker(
    MainPageController_0.saveUser(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "saveUser",
      Nil,
      "POST",
      this.prefix + """register""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_MainPageController_showLoginForm5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_MainPageController_showLoginForm5_invoker = createInvoker(
    MainPageController_0.showLoginForm(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "showLoginForm",
      Nil,
      "GET",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_MainPageController_login6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_MainPageController_login6_invoker = createInvoker(
    MainPageController_0.login(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "login",
      Nil,
      "POST",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_MainPageController_logout7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_MainPageController_logout7_invoker = createInvoker(
    MainPageController_0.logout(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "logout",
      Nil,
      "GET",
      this.prefix + """logout""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_MainPageController_debugUsers8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("debug2")))
  )
  private[this] lazy val controllers_MainPageController_debugUsers8_invoker = createInvoker(
    MainPageController_0.debugUsers(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MainPageController",
      "debugUsers",
      Nil,
      "GET",
      this.prefix + """debug2""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_ToDoListController_get_all9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks")))
  )
  private[this] lazy val controllers_ToDoListController_get_all9_invoker = createInvoker(
    ToDoListController_1.get_all(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "get_all",
      Nil,
      "GET",
      this.prefix + """tasks""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_ToDoListController_create10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/create")))
  )
  private[this] lazy val controllers_ToDoListController_create10_invoker = createInvoker(
    ToDoListController_1.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "create",
      Nil,
      "GET",
      this.prefix + """tasks/create""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_ToDoListController_get_task11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ToDoListController_get_task11_invoker = createInvoker(
    ToDoListController_1.get_task(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "get_task",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """tasks/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ToDoListController_edit12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/edit/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ToDoListController_edit12_invoker = createInvoker(
    ToDoListController_1.edit(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "edit",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """tasks/edit/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_ToDoListController_update13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/edit")))
  )
  private[this] lazy val controllers_ToDoListController_update13_invoker = createInvoker(
    ToDoListController_1.update(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "update",
      Nil,
      "POST",
      this.prefix + """tasks/edit""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_ToDoListController_save14_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/create")))
  )
  private[this] lazy val controllers_ToDoListController_save14_invoker = createInvoker(
    ToDoListController_1.save(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "save",
      Nil,
      "POST",
      this.prefix + """tasks/create""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_ToDoListController_delete_one15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasks/delete/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ToDoListController_delete_one15_invoker = createInvoker(
    ToDoListController_1.delete_one(fakeValue[Int]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "delete_one",
      Seq(classOf[Int]),
      "GET",
      this.prefix + """tasks/delete/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_ToDoListController_deleted16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleted")))
  )
  private[this] lazy val controllers_ToDoListController_deleted16_invoker = createInvoker(
    ToDoListController_1.deleted(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "deleted",
      Nil,
      "GET",
      this.prefix + """deleted""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_ToDoListController_clear17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clear/"), DynamicPart("tableName", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ToDoListController_clear17_invoker = createInvoker(
    ToDoListController_1.clear(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "clear",
      Seq(classOf[String]),
      "GET",
      this.prefix + """clear/""" + "$" + """tableName<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private[this] lazy val controllers_ToDoListController_debugToDoList18_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("debug1")))
  )
  private[this] lazy val controllers_ToDoListController_debugToDoList18_invoker = createInvoker(
    ToDoListController_1.debugToDoList(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ToDoListController",
      "debugToDoList",
      Nil,
      "GET",
      this.prefix + """debug1""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:3
    case controllers_MainPageController_weclomePage0_route(params@_) =>
      call { 
        controllers_MainPageController_weclomePage0_invoker.call(MainPageController_0.weclomePage())
      }
  
    // @LINE:4
    case controllers_MainPageController_firstTask1_route(params@_) =>
      call { 
        controllers_MainPageController_firstTask1_invoker.call(MainPageController_0.firstTask())
      }
  
    // @LINE:5
    case controllers_ToDoListController_save2_route(params@_) =>
      call { 
        controllers_ToDoListController_save2_invoker.call(ToDoListController_1.save())
      }
  
    // @LINE:6
    case controllers_MainPageController_showRegistrationForm3_route(params@_) =>
      call { 
        controllers_MainPageController_showRegistrationForm3_invoker.call(MainPageController_0.showRegistrationForm())
      }
  
    // @LINE:7
    case controllers_MainPageController_saveUser4_route(params@_) =>
      call { 
        controllers_MainPageController_saveUser4_invoker.call(MainPageController_0.saveUser())
      }
  
    // @LINE:8
    case controllers_MainPageController_showLoginForm5_route(params@_) =>
      call { 
        controllers_MainPageController_showLoginForm5_invoker.call(MainPageController_0.showLoginForm())
      }
  
    // @LINE:9
    case controllers_MainPageController_login6_route(params@_) =>
      call { 
        controllers_MainPageController_login6_invoker.call(MainPageController_0.login())
      }
  
    // @LINE:10
    case controllers_MainPageController_logout7_route(params@_) =>
      call { 
        controllers_MainPageController_logout7_invoker.call(MainPageController_0.logout())
      }
  
    // @LINE:11
    case controllers_MainPageController_debugUsers8_route(params@_) =>
      call { 
        controllers_MainPageController_debugUsers8_invoker.call(MainPageController_0.debugUsers())
      }
  
    // @LINE:16
    case controllers_ToDoListController_get_all9_route(params@_) =>
      call { 
        controllers_ToDoListController_get_all9_invoker.call(ToDoListController_1.get_all())
      }
  
    // @LINE:17
    case controllers_ToDoListController_create10_route(params@_) =>
      call { 
        controllers_ToDoListController_create10_invoker.call(ToDoListController_1.create())
      }
  
    // @LINE:18
    case controllers_ToDoListController_get_task11_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_ToDoListController_get_task11_invoker.call(ToDoListController_1.get_task(id))
      }
  
    // @LINE:19
    case controllers_ToDoListController_edit12_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_ToDoListController_edit12_invoker.call(ToDoListController_1.edit(id))
      }
  
    // @LINE:20
    case controllers_ToDoListController_update13_route(params@_) =>
      call { 
        controllers_ToDoListController_update13_invoker.call(ToDoListController_1.update())
      }
  
    // @LINE:21
    case controllers_ToDoListController_save14_route(params@_) =>
      call { 
        controllers_ToDoListController_save14_invoker.call(ToDoListController_1.save())
      }
  
    // @LINE:22
    case controllers_ToDoListController_delete_one15_route(params@_) =>
      call(params.fromPath[Int]("id", None)) { (id) =>
        controllers_ToDoListController_delete_one15_invoker.call(ToDoListController_1.delete_one(id))
      }
  
    // @LINE:23
    case controllers_ToDoListController_deleted16_route(params@_) =>
      call { 
        controllers_ToDoListController_deleted16_invoker.call(ToDoListController_1.deleted())
      }
  
    // @LINE:24
    case controllers_ToDoListController_clear17_route(params@_) =>
      call(params.fromPath[String]("tableName", None)) { (tableName) =>
        controllers_ToDoListController_clear17_invoker.call(ToDoListController_1.clear(tableName))
      }
  
    // @LINE:25
    case controllers_ToDoListController_debugToDoList18_route(params@_) =>
      call { 
        controllers_ToDoListController_debugToDoList18_invoker.call(ToDoListController_1.debugToDoList())
      }
  }
}
