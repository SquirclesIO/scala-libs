package pi.prelude.bindings.json.circe

import io.circe.Codec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.nel.{NEL, nel}
import zio.prelude.NonEmptyList

class NonEmptyListJsonCodecTest extends AnyFunSuite with should.Matchers {
	test("NonEmptyList json serialise/deserialise") {
		import NonEmptyListJsonCodec.nonemptylistJsonCodec
		import io.circe.syntax._
		import io.circe.generic.semiauto.deriveCodec

		implicit val totoCodec: Codec[Toto] = deriveCodec

		val value = nel(Toto("tutu"))

		value.asJson.as[NEL[Toto]] shouldBe Right(value)
	}

	test("json to NEL") {
		import io.circe.parser._
		import NonEmptyListJsonCodec.nonemptylistJsonCodec

		val nodeJson = """["toto"]"""
		val result = decode[NEL[String]](nodeJson)
		result shouldBe Right(NonEmptyList("toto"))
	}

	case class Toto(titi: String)
}