package controllers

import com.google.inject.Inject
import play.api.mvc._
import play.mvc.Http.MimeTypes

class Application @Inject() (environment: play.api.Environment) extends Controller {

  def index = Action {
    Ok(pages.HelloWorld.getPage(environment.mode).toString).as(MimeTypes.HTML)
  }

}
