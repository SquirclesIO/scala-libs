package pi.prelude.bindings.json.circe

import io.circe.{DecodingFailure, Json}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.safeuuid.SafeUUID
import io.circe.syntax._


class SafeUUIDJsonCodecTest extends AnyFunSuite with should.Matchers {
	import SafeUUIDJsonCodec.safeUUIDJsonCodec

	test("SafeUUID Json read") {
		val uid = SafeUUID.generate.toString
		Json.fromString(uid).as[SafeUUID] shouldBe Right(uid)
	}

	test("SafeUUID Json read error") {
		val uid = "toto"
		Json.fromString(uid).as[SafeUUID] shouldBe Left(DecodingFailure("[toto] has not the good format (need SHA-256 String format)", List()))
	}

	test("SafeUUID Json write") {
		val uid = SafeUUID.generate

		uid.asJson shouldBe Json.fromString(uid.toString)
	}
}
