// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseMainPageController MainPageController = new controllers.ReverseMainPageController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseToDoListController ToDoListController = new controllers.ReverseToDoListController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseMainPageController MainPageController = new controllers.javascript.ReverseMainPageController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseToDoListController ToDoListController = new controllers.javascript.ReverseToDoListController(RoutesPrefix.byNamePrefix());
  }

}
