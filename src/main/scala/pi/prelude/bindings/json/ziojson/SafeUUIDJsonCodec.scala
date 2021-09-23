package pi.prelude.bindings.json.ziojson

import pi.prelude.safeuuid.SafeUUID
import zio.json._

object SafeUUIDJsonCodec {
	implicit val safeUUIDJsonDecoder: JsonDecoder[SafeUUID] = JsonDecoder[String].mapOrFail { SafeUUID.apply }
	implicit val safeUUIDJsonEncoder: JsonEncoder[SafeUUID] = JsonEncoder[String].contramap { _.safeValue }
}