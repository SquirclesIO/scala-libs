package pi.prelude.bindings.json.ziojson

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.string.Login

import scala.annotation.nowarn

@nowarn("msg=discarded non-Unit value")
class LoginJsonCodecTest extends AnyFunSuite with should.Matchers {
    test("should automatically derive Percentage") {
        import zio.json._
        import LoginJsonCodec._

        implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
        implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

        Login("login").map { Toto(_).toJson } shouldBe Right("""{"l":"login"}""")
        """{"l":"login"}""".fromJson[Toto] shouldBe Login("login").map { Toto.apply }
    }

    case class Toto(l: Login)
}
