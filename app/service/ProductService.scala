package service

import model.Product
import scala.concurrent.Future
import service.impl.ProductServiceImpl
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[ProductServiceImpl])
trait ProductService {

  def findById(id: Long): Future[Option[Product]]
  def all(): Future[List[Product]]

}
