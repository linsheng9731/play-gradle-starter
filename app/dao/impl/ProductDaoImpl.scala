package dao.impl

import dao.ProductDao
import model.Product
import slick.jdbc.JdbcProfile
import scala.concurrent.Future
import dao.component.ProductComponent
import slick.jdbc.PostgresProfile.api._
import javax.inject.{ Inject, Singleton }
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }

@Singleton
class ProductDaoImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
  extends ProductDao with ProductComponent with HasDatabaseConfigProvider[JdbcProfile] {

  override def findById(id: Long): Future[Option[Product]] = {
    db.run(Products.filter(_.id === id).result.headOption)
  }

  override def all(): Future[List[Product]] = {
    db.run(Products.result.map(_.toList))
  }

}
