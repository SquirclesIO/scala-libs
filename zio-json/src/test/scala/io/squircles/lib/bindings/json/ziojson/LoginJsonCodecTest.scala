package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.string.Login
import zio.test.{assertTrue, ZIOSpecDefault}

object LoginJsonCodecTest extends ZIOSpecDefault {
    val spec = suite("LoginJsonCodec")(
        test("should automatically derive Percentage") {
            import LoginJsonCodec.*
            import zio.json.*

            implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
            implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

            assertTrue(Login("login").map {
                Toto(_).toJson
            } == Right("""{"l":"login"}""")) &&
            assertTrue("""{"l":"login"}""".fromJson[Toto] == Login("login").map {
                Toto.apply
            })
        }
    )

    case class Toto(l: Login)
}
