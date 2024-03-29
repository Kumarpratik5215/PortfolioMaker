// app/controllers/HomeController.scala
package controllers

import play.api.mvc._
import play.api.i18n._
import javax.inject._
import model.User

@Singleton
class HomeController @Inject()(cc: ControllerComponents, messagesApi: MessagesApi) extends AbstractController(cc) with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.main())
  }

  def lowRisk = Action { implicit request =>
    Ok(views.html.stocks.lowRisk())
  }

  def mediumRisk = Action { implicit request =>
    Ok(views.html.stocks.mediumRisk())
  }

  def highRisk = Action { implicit request =>
    Ok(views.html.stocks.highRisk())
  }


  def buyNow: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.stocks.buyNow())
  }

//def buyNow: Action[AnyContent] = Action { implicit request =>
//  Ok(buyNow()) // Render the buyNow view using the apply method
//}


  def sharesWithPE :  Action[AnyContent] = Action { implicit request =>
    Ok(views.html.stocks.sharesWithPE())
  }

}
