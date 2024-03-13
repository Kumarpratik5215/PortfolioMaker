package model

import javax.swing.text.PasswordView
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.{Success, Failure}
//import scala.concurrent.ExecutionContext.Implicits.global
case class Users(id: Long, name: String, password: String)
object SlickTables{

  val profile = slick.jdbc.PostgresProfile
  import slick.jdbc.PostgresProfile.api._

  class UserTable(tag: Tag) extends Table[Users](tag,Some("users")/* <- schema name */,"User"){
    def id = column[Long]("user_id",O.PrimaryKey,O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")

    override def * = (id,username, password) <> (Users.tupled,Users.unapply)
  }

  lazy val userTable = TableQuery[UserTable]

  def insertUser(user: Users): DBIO[Int] = {
    userTable += user
  }

  // Assuming you have a User case class and a UserTable defined as before...
  object Connection {
    val db = Database.forConfig("postgres")
  }

  object User {

    def authenticate(username: String, password: String)(implicit ec: ExecutionContext): Future[Boolean] = {

      val query = SlickTables.userTable.filter(user => user.username === username && user.password === password)
      val result: Future[Seq[Users]] = Connection.db.run(query.result)

      // Check if there is a matching user in the database
      result.map(_.nonEmpty)
    }
  }


}
