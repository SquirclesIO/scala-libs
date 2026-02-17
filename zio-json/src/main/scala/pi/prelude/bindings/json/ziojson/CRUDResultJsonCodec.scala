package pi.prelude.bindings.json.ziojson

import pi.prelude.http.CRUDResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object CRUDResultJsonCodec {
    import SafeUUIDJsonCodec.{safeUUIDJsonDecoder, safeUUIDJsonEncoder}

    implicit val crudResultJsonDecoder: JsonDecoder[CRUDResult] = DeriveJsonDecoder.gen[CRUDResult]
    implicit val crudResultJsonEncoder: JsonEncoder[CRUDResult] = DeriveJsonEncoder.gen[CRUDResult]
}
