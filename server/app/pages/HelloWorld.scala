package pages

import play.api.Mode._
import scalatags.Text.all._
import shared.Api

import scalatags.Text.TypedTag

object HelloWorld {
  var content =
    div(id:="contents",
      h2("Play and Scala.js share the same message"),
      ul(
        li("Play shouts out:", em("nothing")),
        li("Scala.js shouts out:", em(id:="scalajsShoutOut"))
      )
    )

  def getPage(mode: Mode) =
    html(
      head(
        link(
          rel:="stylesheet",
          media:="screen",
          href:=controllers.routes.Assets.at("stylesheets/main.css").url
        ),
        link(
          rel:="shortcut icon",
          media:="image/png",
          href:=controllers.routes.Assets.at("images/favicon.png").url
        )
      ),
      body(
        content,
        scripts("client", mode)
      )
    )

  def scripts(projectName: String, mode: Mode) = Seq(selectScript(projectName, mode), launcher(projectName))

  def selectScript(projectName: String, mode: Mode): TypedTag[String] = {
    if (mode == Prod) {
      script(src := s"/assets/${projectName.toLowerCase}-opt.js", `type` := "text/javascript")
    } else {
      script(src := s"/assets/${projectName.toLowerCase}-fastopt.js", `type` := "text/javascript")
    }
  }

  def launcher(projectName: String) = {
    script(src := s"/assets/${projectName.toLowerCase}-launcher.js", `type` := "text/javascript")
  }

}
