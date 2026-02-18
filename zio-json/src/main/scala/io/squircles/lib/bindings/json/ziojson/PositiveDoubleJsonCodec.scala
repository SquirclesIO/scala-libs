package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.number._
import zio.json._

object PositiveDoubleJsonCodec {
    implicit val positiveDoubleJsonDecoder: JsonDecoder[PositiveDouble] = JsonDecoder[Double].mapOrFail { PositiveDouble.either }
    implicit val positiveDoubleJsonEncoder: JsonEncoder[PositiveDouble] = JsonEncoder[Double].contramap { _.toDouble }
}
