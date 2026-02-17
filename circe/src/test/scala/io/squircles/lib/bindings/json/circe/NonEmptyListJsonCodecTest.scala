package io.squircles.lib.bindings.json.circe

import io.circe.Codec
import io.squircles.lib.nel.{nel, NEL}
import zio.prelude.NonEmptyList
import zio.test.{assertTrue, ZIOSpecDefault}

object NonEmptyListJsonCodecTest extends ZIOSpecDefault {
    val spec = suite("NonEmptyListJsonCodec")(
        test("NonEmptyList json serialise/deserialise") {
            import NonEmptyListJsonCodec.nonemptylistJsonCodec
            import io.circe.syntax._
            import io.circe.generic.semiauto.deriveCodec

            implicit val totoCodec: Codec[Toto] = deriveCodec

            val value = nel(Toto("tutu"))

            assertTrue(value.asJson.as[NEL[Toto]] == Right(value))
        },
        test("json to NEL") {
            import io.circe.parser._
            import NonEmptyListJsonCodec.nonemptylistJsonCodec

            val nodeJson = """["toto"]"""
            val result = decode[NEL[String]](nodeJson)
            assertTrue(result == Right(NonEmptyList("toto")))
        }
    )

    case class Toto(titi: String)
}
