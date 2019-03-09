package controllers

import org.junit.runner.RunWith
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.junit.JUnitRunner

// https://stackoverflow.com/questions/18823855/cant-run-scalatest-with-gradle
@RunWith(classOf[JUnitRunner])
class HelloControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HelloController GET" should {

    "render the hello world page from a new instance of controller" in {
            val controller = new HelloController(stubControllerComponents())
            val hello = controller.hello().apply(FakeRequest(GET, "/"))
            status(hello) mustBe OK
            contentType(hello) mustBe Some("text/plain")
            contentAsString(hello) must include("hello world!")
    }

  }
}
