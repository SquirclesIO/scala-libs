package pi.prelude.bindings.json.ziojson

import pi.prelude.number.{PositiveInt, PositiveIntPredicate}
import zio.json.{JsonDecoder, JsonEncoder}

object PositiveIntJsonCodec {
	import zio.json.interop.refined._

	/**
	 * Use `import zio.json.interop.refined._` from `"dev.zio" %% "zio-json-interop-refined" % "0.2.0-M3"`
	 * to perform zio-json encoding/decoding directly
	 * */

	implicit val positiveIntJsonDecoder: JsonDecoder[PositiveInt] = decodeRefined[Int, PositiveIntPredicate]
	implicit val positiveIntJsonEncoder: JsonEncoder[PositiveInt] = encodeRefined[Int, PositiveIntPredicate]
}