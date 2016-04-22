package browser
  import scala.scalajs.js
  import org.scalajs.dom
  import shared.Api
  import autowire._
  import scalajs.concurrent.JSExecutionContext.Implicits.queue

object Ajaxer extends autowire.Client[String, upickle.Reader, upickle.Writer]{
  override def doCall(req: Request) = {
    dom.ext.Ajax.get(
      url = "/ajax/" + req.path.mkString("/"),
      data = upickle.write(req.args)
    ).map(_.responseText)
  }

  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)
  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
}

object Index extends js.JSApp {
  def main(): Unit = {
    val elementToUpdate = dom.document.getElementById("scalajsShoutOut")
    def update() = Ajaxer[Api].randomNum().call().onSuccess {
      case value => elementToUpdate.textContent = value.toString()
    }
    elementToUpdate.textContent = "waiting for a value"
    update()
  }
}
