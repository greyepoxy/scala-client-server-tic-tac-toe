package test.browser
import browser.Index
import utest._

object IndexTestSuite extends TestSuite {
  val tests = this{
    'A-{
      assert(Index.getWaitingString == "waiting for a value")
    }
  }
}
