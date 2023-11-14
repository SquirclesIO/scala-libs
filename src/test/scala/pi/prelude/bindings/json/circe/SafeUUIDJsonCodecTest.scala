package pi.prelude.bindings.json.circe

import io.circe.syntax._
import io.circe.{DecodingFailure, Json}
import pi.prelude.safeuuid.SafeUUID
import zio.test.{assertTrue, ZIOSpecDefault}

object SafeUUIDJsonCodecTest extends ZIOSpecDefault {
    import SafeUUIDJsonCodec.safeUUIDJsonCodec

    val spec = suite("SafeUUIDJsonCodec")(
        test("SafeUUID Json read") {
            val uid = SafeUUID.generate
            assertTrue(Json.fromString(uid.toString).as[SafeUUID] == Right(uid))
        },
        test("SafeUUID Json read error") {
            val uid = "toto"
            assertTrue(Json.fromString(uid).as[SafeUUID] == Left(DecodingFailure(
                "[toto] has not the good format (need SHA-256 String format)",
                List()
            )))
        },
        test("SafeUUID Json write") {
            val uid = SafeUUID.generate

            assertTrue(uid.asJson == Json.fromString(uid.toString))
        }
    )
}
