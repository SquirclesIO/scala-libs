package pi.prelude.bindings.json.ziojson

import pi.prelude.number.{Percentage, PourcentagePredicate}
import zio.json.{JsonDecoder, JsonEncoder}

object PercentageJsonCodec {
    import zio.json.interop.refined._

    /** Use `import zio.json.interop.refined._` from `"dev.zio" %% "zio-json-interop-refined" % "0.2.0-M3"` to perform zio-json
      * encoding/decoding directly
      */

    implicit val percentageJsonDecoder: JsonDecoder[Percentage] = decodeRefined[Double, PourcentagePredicate]
    implicit val percentageJsonEncoder: JsonEncoder[Percentage] = encodeRefined[Double, PourcentagePredicate]
}
