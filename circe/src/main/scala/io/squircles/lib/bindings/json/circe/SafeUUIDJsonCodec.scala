package io.squircles.lib.bindings.json.circe

import io.circe.Decoder.Result
import io.circe.{Codec, DecodingFailure, HCursor, Json}
import io.squircles.lib.safeuuid.SafeUUID

object SafeUUIDJsonCodec {
    import io.squircles.lib.safeuuid.SafeUUIDSyntax

    implicit val safeUUIDJsonCodec: Codec[SafeUUID] = new Codec[SafeUUID] {
        override def apply(a: SafeUUID): Json = Json.fromString(a.safeValue)
        override def apply(c: HCursor): Result[SafeUUID] =
            c.as[String].flatMap { SafeUUID(_).left.map { s => DecodingFailure(s, Nil) } }
    }
}
