package form

import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads, Writes}
/**
  * ProductForm
  *
  * @author damon lin
  *         2019/3/7
  */

final case class ProductForm(id: Long, name: String)

object ProductForm {

  lazy val nameMaxLength = 100

  implicit val reads: Reads[ProductForm] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "name").read[String](maxLength[String](nameMaxLength)))(ProductForm.apply _)

  implicit val writes: Writes[ProductForm] = (
    (JsPath \ "id").write[Long] and
      (JsPath \ "name").write[String])(unlift(ProductForm.unapply))

}
