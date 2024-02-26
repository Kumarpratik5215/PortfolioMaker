// app/controllers/UserCrud.scala
package controllers

import model.SlickTables.profile.api._
import javax.inject._
import play.api.mvc._
import play.api.i18n._
import model.SlickTables._
import model.Users


import scala.concurrent.ExecutionContext

@Singleton
class UserCrud @Inject()(cc: ControllerComponents, messagesApi: MessagesApi, assetsFinder: AssetsFinder) extends AbstractController(cc) with I18nSupport {

  // Implicit ExecutionContext for async operations
  implicit private val ec: ExecutionContext = cc.executionContext

  def createUser(username: String, password: String): Action[AnyContent] = Action.async { implicit request =>
    val newUser = Users(id = 0, username, password)
    val insertAction = insertUser(newUser)

    Connection.db.run(insertAction).map { _ =>
      Ok(s"User $username created successfully!")
    }.recover {
      case ex => InternalServerError(s"Failed to create user: ${ex.getMessage}")
    }
  }

  def getUsers: Action[AnyContent] = Action.async { implicit request =>
    val query = userTable.result
    Connection.db.run(query).map { users =>
      Ok(s"Users: $users")
    }.recover {
      case ex => InternalServerError(s"Failed to retrieve users: ${ex.getMessage}")
    }
  }

  def updateUser(id: Long, newPassword: String): Action[AnyContent] = Action.async { implicit request =>
    val updateAction = userTable.filter(_.id === id).map(_.password).update(newPassword)

    Connection.db.run(updateAction).map { _ =>
      Ok(s"User with ID $id updated successfully!")
    }.recover {
      case ex => InternalServerError(s"Failed to update user: ${ex.getMessage}")
    }
  }

  def deleteUser(id: Long): Action[AnyContent] = Action.async { implicit request =>
    val deleteAction = userTable.filter(_.id === id).delete

    Connection.db.run(deleteAction).map { _ =>
      Ok(s"User with ID $id deleted successfully!")
    }.recover {
      case ex => InternalServerError(s"Failed to delete user: ${ex.getMessage}")
    }
  }
//
//    def main(args: Array[String]): Unit = {
//      createUser("pratik", "123")
//    //  updateUser()
//
//    }

}
