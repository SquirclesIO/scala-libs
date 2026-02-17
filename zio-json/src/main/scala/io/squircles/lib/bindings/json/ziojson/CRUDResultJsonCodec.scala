package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.http.CRUDResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object CRUDResultJsonCodec {
    import SafeUUIDJsonCodec.{safeUUIDJsonDecoder, safeUUIDJsonEncoder}

    implicit val crudResultJsonDecoder: JsonDecoder[CRUDResult] = DeriveJsonDecoder.gen[CRUDResult]
    implicit val crudResultJsonEncoder: JsonEncoder[CRUDResult] = DeriveJsonEncoder.gen[CRUDResult]
}
