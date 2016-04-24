package app.example
import utest._

object ExampleTests extends TestSuite{
  val tests = this{
    'A{
      val thing = 5
      assert(5 == thing)
    }
  }

}
