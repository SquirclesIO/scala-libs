package pi.prelude.bindings.json.ziojson

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.string.Login
import pi.prelude.bindings.json.ziojson.LoginJsonCodec

class LoginJsonCodecTest extends AnyFunSuite with should.Matchers {
	test("should automatically derive Percentage") {
		import zio.json._
		import LoginJsonCodec._

		implicit val TotoJsonEncoder = DeriveJsonEncoder.gen[Toto]
		implicit val TotoJsonDecoder = DeriveJsonDecoder.gen[Toto]

		Login("login").map { Toto(_).toJson } shouldBe Right("""{"l":"login"}""")
		"""{"l":"login"}""".fromJson[Toto] shouldBe Login("login").map { Toto }
	}

	case class Toto(l: Login)
}