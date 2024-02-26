// app/models/User.scala
package model

object User {
  // Replace this with actual authentication logic
  def authenticate(username: String, password: String): Boolean = {
    // Example: Check if username and password match a user in your database
    // For simplicity, use hardcoded values in this example
    username == "demo" && password == "password"
  }
}
