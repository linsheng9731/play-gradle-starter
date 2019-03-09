package dao.component

import model.Product
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.{ Rep, Tag }
import slick.jdbc.PostgresProfile.api._

trait ProductComponent {
  self: HasDatabaseConfigProvider[JdbcProfile] ⇒

  class ProductTable(tag: Tag) extends Table[Product](tag, "products") {

    def id: Rep[Long] = column[Long]("id", O.PrimaryKey)

    def name: Rep[String] = column[String]("name")

    override def * = (id, name) <> (Product.tupled, Product.unapply)
  }

  val Products = TableQuery[ProductTable]

}
