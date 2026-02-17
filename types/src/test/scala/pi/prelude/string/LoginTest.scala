package pi.prelude.string

import zio.test.{assertTrue, ZIOSpecDefault}

object LoginTest extends ZIOSpecDefault {
    val spec = suite("Login")(
        test("empty string should be rejected as Login") {
            assertTrue(Login("") == Left("Should not be empty"))
        },
        test("login with space should be trimmed") {
            assertTrue(Login("monLogin ").asInstanceOf[Either[String, String]] == Right("monLogin"))
        },
        test("ominal case") {
            assertTrue(Login("monLogin").asInstanceOf[Either[String, String]] == Right("monLogin"))
        }
    )
}
