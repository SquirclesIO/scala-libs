package pi.prelude.bindings.json.ziojson

import pi.prelude.nel._
import zio.json._

object NonEmptyListJsonCodec {
    implicit def nelJsonDecoder[A: JsonDecoder]: JsonDecoder[NEL[A]] =
        JsonDecoder[Seq[A]].mapOrFail { _.toNEL.toRight("should be nonempty") }
    implicit def nelJsonEncoder[A: JsonEncoder]: JsonEncoder[NEL[A]] = JsonEncoder[Seq[A]].contramap { _.toList }
}
