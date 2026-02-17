package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.collection.nonemptyset._
import zio.json.{JsonDecoder, JsonEncoder}

object NonEmptySetJsonCodec {
    given nesJsonDecoder[A: JsonDecoder]: JsonDecoder[NES[A]] =
        JsonDecoder[Seq[A]].mapOrFail { _.toNES.toRight("should be nonempty") }

    given nesJsonEncoder[A: JsonEncoder: Ordering]: JsonEncoder[NES[A]] =
        JsonEncoder[Seq[A]].contramap { _.toList.sorted }
}
