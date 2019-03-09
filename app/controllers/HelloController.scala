package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

/**
  * HelloController
  *
  * @author damon lin
  *         2019/3/8
  */
@Singleton
class HelloController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder) extends AbstractController(cc) {

  def hello(): Action[AnyContent] = Action { _ ⇒
    Ok("hello world!")
  }

  def index()=  Action {
    Ok(views.html.index("Your new application is ready."))
  }

}