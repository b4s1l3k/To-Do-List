// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:3
package controllers.javascript {

  // @LINE:3
  class ReverseMainPageController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:3
    def weclomePage: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.weclomePage",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:6
    def showRegistrationForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.showRegistrationForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
        }
      """
    )
  
    // @LINE:4
    def firstTask: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.firstTask",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "main"})
        }
      """
    )
  
    // @LINE:7
    def saveUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.saveUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
        }
      """
    )
  
    // @LINE:10
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.logout",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:8
    def showLoginForm: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.showLoginForm",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
    // @LINE:11
    def debugUsers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.debugUsers",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "debug2"})
        }
      """
    )
  
    // @LINE:9
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MainPageController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }

  // @LINE:5
  class ReverseToDoListController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def delete_one: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.delete_one",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks/delete/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:17
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.create",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks/create"})
        }
      """
    )
  
    // @LINE:16
    def get_all: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.get_all",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks"})
        }
      """
    )
  
    // @LINE:25
    def debugToDoList: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.debugToDoList",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "debug1"})
        }
      """
    )
  
    // @LINE:19
    def edit: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.edit",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks/edit/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:5
    def save: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.save",
      """
        function() {
        
          if (true) {
            return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "main"})
          }
        
        }
      """
    )
  
    // @LINE:23
    def deleted: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.deleted",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "deleted"})
        }
      """
    )
  
    // @LINE:20
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.update",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks/edit"})
        }
      """
    )
  
    // @LINE:24
    def clear: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.clear",
      """
        function(tableName0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clear/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("tableName", tableName0))})
        }
      """
    )
  
    // @LINE:18
    def get_task: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ToDoListController.get_task",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tasks/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Int]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }


}
