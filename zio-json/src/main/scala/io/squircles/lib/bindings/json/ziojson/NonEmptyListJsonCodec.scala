package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.nel._
import zio.json._

object NonEmptyListJsonCodec {
    given nelJsonDecoder[A: JsonDecoder]: JsonDecoder[NEL[A]] =
        JsonDecoder[Seq[A]].mapOrFail { _.toNEL.toRight("should be nonempty") }

    given nelJsonEncoder[A: JsonEncoder]: JsonEncoder[NEL[A]] =
        JsonEncoder[Seq[A]].contramap { _.toList }
}
