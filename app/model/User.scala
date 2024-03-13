// app/models/User.scala
package model

object User {

  def authenticate(username: String, password: String): Boolean = {

    username == "demo" && password == "password"
  }
}
