package io.squircles.lib.bindings.json.circe

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import io.squircles.lib.http.CRUDResult

object CRUDResultJsonCodec {
    import io.squircles.lib.bindings.json.circe.SafeUUIDJsonCodec.safeUUIDJsonCodec
    given codec: Codec[CRUDResult] = deriveCodec
}
