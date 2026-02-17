package pi.prelude.bindings.json.ziojson

import pi.prelude.number._
import zio.json._

object PositiveDoubleJsonCodec {
    implicit val positiveDoubleJsonDecoder: JsonDecoder[PositiveDouble] = JsonDecoder[Double].mapOrFail { PositiveDouble.apply }
    implicit val positiveDoubleJsonEncoder: JsonEncoder[PositiveDouble] = JsonEncoder[Double].contramap { _.toDouble }
}
