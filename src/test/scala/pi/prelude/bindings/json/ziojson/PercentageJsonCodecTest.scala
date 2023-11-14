package pi.prelude.bindings.json.ziojson

import pi.prelude.number.Percentage
import zio.test.{assertTrue, ZIOSpecDefault}

object PercentageJsonCodecTest extends ZIOSpecDefault {
    val spec = suite("PercentageJsonCodec")(
        test("should automatically derive Percentage") {
            import zio.json.interop.refined._
            import zio.json._

            implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
            implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

            assertTrue(Percentage(12).map {
                Toto(_).toJson
            } == Right("""{"p":12.0}""")) &&
            assertTrue("""{"p":12.0}""".fromJson[Toto] == Percentage(12).map {
                Toto.apply
            })
        },
        test("do same with custom decoder/encoder") {
            import zio.json._
            import PercentageJsonCodec._

            implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
            implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

            assertTrue(Percentage(12).map {
                Toto(_).toJson
            } == Right("""{"p":12.0}""")) &&
            assertTrue("""{"p":12.0}""".fromJson[Toto] == Percentage(12).map {
                Toto.apply
            }) &&
            assertTrue(Percentage(12).map {
                Toto.apply
            }.map {
                _.toJson.fromJson[Toto]
            } == Right(Percentage(12).map {
                Toto.apply
            }))
        }
    )

    case class Toto(p: Percentage)
}
