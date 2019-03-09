package service.impl

import dao.ProductDao
import model.Product
import javax.inject.{Inject, Singleton}
import service.ProductService
import scala.concurrent.Future

@Singleton
class ProductServiceImpl @Inject() (ProductDao: ProductDao) extends ProductService {

  override def findById(id: Long): Future[Option[Product]] = ProductDao.findById(id)

  override def all(): Future[List[Product]] = ProductDao.all()
}
