package dao

import com.google.inject.ImplementedBy
import dao.impl.ProductDaoImpl
import model.Product
import scala.concurrent.Future

@ImplementedBy(classOf[ProductDaoImpl])
trait ProductDao {

  def findById(id: Long): Future[Option[Product]]
  def all(): Future[List[Product]]

}
