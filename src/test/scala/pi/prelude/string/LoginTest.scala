package pi.prelude.string

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class LoginTest extends AnyFunSuite with should.Matchers {
    test("empty string should be rejected as Login") {
        Login("") shouldBe Left("Should not be empty")
    }

    test("login with space should be trimmed") {
        Login("monLogin ") shouldBe Right("monLogin")
    }

    test("ominal case") {
        Login("monLogin") shouldBe Right("monLogin")
    }
}
