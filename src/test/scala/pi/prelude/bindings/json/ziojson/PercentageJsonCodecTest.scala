package pi.prelude.bindings.json.ziojson

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.number.Percentage

import scala.annotation.nowarn

@nowarn("msg=discarded non-Unit value")
class PercentageJsonCodecTest extends AnyFunSuite with should.Matchers {
    test("should automatically derive Percentage") {
        import zio.json.interop.refined._
        import zio.json._

        implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
        implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

        Percentage(12).map { Toto(_).toJson } shouldBe Right("""{"p":12.0}""")
        """{"p":12.0}""".fromJson[Toto] shouldBe Percentage(12).map { Toto.apply }
    }

    test("do same with custom decoder/encoder") {
        import zio.json._
        import PercentageJsonCodec._

        implicit val TotoJsonEncoder: JsonEncoder[Toto] = DeriveJsonEncoder.gen
        implicit val TotoJsonDecoder: JsonDecoder[Toto] = DeriveJsonDecoder.gen

        Percentage(12).map { Toto(_).toJson } shouldBe Right("""{"p":12.0}""")
        """{"p":12.0}""".fromJson[Toto] shouldBe Percentage(12).map { Toto.apply }

        Percentage(12).map { Toto.apply }.map { _.toJson.fromJson[Toto] } shouldBe Right(Percentage(12).map { Toto.apply })
    }

    case class Toto(p: Percentage)
}
