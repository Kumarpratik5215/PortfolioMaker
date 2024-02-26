//// app/controllers/AuthController.scala
//package controllers
//
//import play.api.mvc._
//import play.api.i18n._
//import javax.inject._
//import model.User
//import play.twirl.api.Html
//import controllers.AssetsFinder
//
//
//@Singleton
//class AuthController @Inject()(cc: ControllerComponents, messagesApi: MessagesApi, assetsFinder: AssetsFinder) extends AbstractController(cc) with I18nSupport {
//
//  def login = Action { implicit request =>
//    //Ok(views.html.auth.login(""))
//  //  Ok(views.html.auth.login("")(Html("")))
//    Ok(views.html.auth.login("")(Html(""))(assetsFinder))
//  }
//
//  def authenticate = Action { implicit request =>
//    val postVals = request.body.asFormUrlEncoded
//    postVals.map { args =>
//      val username = args("username").head
//      val password = args("password").head
//
////      // authentication logic ..will replace later
////      if (User.authenticate(username, password)) {
////        Redirect(routes.HomeController.index()).withSession("username" -> username)
////      } else {
////        Redirect(routes.AuthController.login()).flashing("error" -> "Invalid username or password")
////      }
//val isAuthenticated = true
//
//      if (isAuthenticated) {
//        Redirect(routes.HomeController.index()).flashing("success" -> "Login successful!")
//      } else {
//        // If authentication fails, redirect with error message
//        Redirect(routes.AuthController.login()).flashing("error" -> "Invalid credentials.")
//      }
//    }.getOrElse(Redirect(routes.AuthController.login()))
//  }
//
//  def logout = Action { implicit request =>
//    Redirect(routes.AuthController.login()).withNewSession
//  }
//}


package controllers

import play.api.mvc._
import play.api.i18n._

import javax.inject._
import play.twirl.api.Html
import controllers.AssetsFinder
import model.Users
import model.SlickTables

@Singleton
class AuthController @Inject()(cc: ControllerComponents, messagesApi: MessagesApi, assetsFinder: AssetsFinder) extends AbstractController(cc) with I18nSupport {

  def login = Action { implicit request =>
    Ok(views.html.auth.login("")(Html(""))(assetsFinder))
  }

  def authenticate = Action { implicit request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("username").head
      val password = args("password").head

      // Placeholder for authentication logic
      //val isAuthenticated = Users.auth(username, password)

      if (true) {
        Redirect(routes.HomeController.index()).flashing("success" -> "Login successful!").withSession("username" -> username)
      } else {
        Redirect(routes.AuthController.login()).flashing("error" -> "Invalid credentials.")
      }
    }.getOrElse(Redirect(routes.AuthController.login()))
  }

  def logout = Action { implicit request =>
    Redirect(routes.AuthController.login()).withNewSession
  }
}


