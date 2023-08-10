// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:3
package controllers {

  // @LINE:3
  class ReverseMainPageController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:3
    def weclomePage(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:6
    def showRegistrationForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "register")
    }
  
    // @LINE:4
    def firstTask(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "main")
    }
  
    // @LINE:7
    def saveUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "register")
    }
  
    // @LINE:10
    def logout(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:8
    def showLoginForm(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "login")
    }
  
    // @LINE:11
    def debugUsers(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "debug2")
    }
  
    // @LINE:9
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:5
  class ReverseToDoListController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def delete_one(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tasks/delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:17
    def create(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tasks/create")
    }
  
    // @LINE:16
    def get_all(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tasks")
    }
  
    // @LINE:25
    def debugToDoList(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "debug1")
    }
  
    // @LINE:19
    def edit(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tasks/edit/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
    // @LINE:5
    def save(): Call = {
    
      () match {
      
        // @LINE:5
        case ()  =>
          
          Call("POST", _prefix + { _defaultPrefix } + "main")
      
      }
    
    }
  
    // @LINE:23
    def deleted(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "deleted")
    }
  
    // @LINE:20
    def update(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "tasks/edit")
    }
  
    // @LINE:24
    def clear(tableName:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "clear/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("tableName", tableName)))
    }
  
    // @LINE:18
    def get_task(id:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tasks/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Int]].unbind("id", id)))
    }
  
  }


}
