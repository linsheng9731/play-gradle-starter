package controllers

import form.ProductForm
import service.ProductService
import play.api.libs.json.Json
import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class ProductController @Inject()(
    cc:             ControllerComponents,
    ProductService: ProductService)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def get(id: Int): Action[AnyContent] = Action.async { implicit request ⇒
    ProductService.findById(id).map {
      case None    ⇒ NotFound
      case Some(p) ⇒ Ok(Json.toJson(ProductForm(p.id, p.name)))
    }
  }

  def index(): Action[AnyContent] = Action.async { implicit request ⇒
    ProductService.all().map {
      case ps ⇒ Ok(Json.toJson(ps.map(p ⇒ ProductForm(p.id, p.name))))
    }
  }

}
