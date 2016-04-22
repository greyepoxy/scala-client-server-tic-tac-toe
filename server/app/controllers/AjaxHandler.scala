package controllers

import play.api.mvc._
import shared.Api
import upickle.Js

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Router extends autowire.Server[Js.Value, upickle.Reader, upickle.Writer]{
  def read[Result: upickle.Reader](p: Js.Value) = upickle.readJs[Result](p)
  def write[Result: upickle.Writer](r: Result) = upickle.writeJs(r)
}

class AjaxHandler extends Controller with Api {
  def handle(apiPath: String) =  Action.async { request =>

    val b = request.body.asText.getOrElse("{}")

    Router.route[Api](AjaxHandler.this).lift(
      autowire.Core.Request(
        apiPath.split("/"),
        upickle.json.read(b).asInstanceOf[Js.Obj].value.toMap)
    ).map( maybeResult => {
      maybeResult.map( result => {
        val data = upickle.json.write(result)
        Ok(data)
      })
    }).getOrElse(Future.successful(NotFound("Not Found")))
  }

  def randomNum(): Int = {
    1
  }
}
