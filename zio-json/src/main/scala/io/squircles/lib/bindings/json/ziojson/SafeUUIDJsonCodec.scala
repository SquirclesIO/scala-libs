package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.safeuuid.SafeUUID
import zio.json._

object SafeUUIDJsonCodec {
    import io.squircles.lib.safeuuid.SafeUUIDSyntax

    implicit val safeUUIDJsonDecoder: JsonDecoder[SafeUUID] = JsonDecoder[String].mapOrFail { SafeUUID.apply }
    implicit val safeUUIDJsonEncoder: JsonEncoder[SafeUUID] = JsonEncoder[String].contramap { _.safeValue }
}
