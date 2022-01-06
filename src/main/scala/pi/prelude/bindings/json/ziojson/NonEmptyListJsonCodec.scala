package pi.prelude.bindings.json.ziojson

import pi.prelude.nel._
import zio.json._

object NonEmptyListJsonCodec {
	implicit def positiveDoubleJsonDecoder[A: JsonDecoder]: JsonDecoder[NEL[A]] = JsonDecoder[Seq[A]].mapOrFail { _.toNEL.toRight("should be nonempty") }
	implicit def positiveDoubleJsonEncoder[A: JsonEncoder]: JsonEncoder[NEL[A]] = JsonEncoder[Seq[A]].contramap { _.toList }
}