//package model
//
//import slick.jdbc.PostgresProfile.api._
//
//object Connection {
//
//  val db = Database.forConfig("postgres")
//}


package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Connection {

  val db = Database.forConfig("postgres")

  // Method to execute database actions
  def execute[T](action: DBIO[T]): T = {
    try {
      val result = db.run(action)
      Await.result(result, Duration.Inf)
    } finally db.close
  }
}
