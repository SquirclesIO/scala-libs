package pi.prelude.bindings.json.circe

import io.circe.Decoder.Result
import io.circe.{Codec, DecodingFailure, HCursor, Json}
import pi.prelude.safeuuid.SafeUUID

object SafeUUIDJsonCodec {
    implicit val safeUUIDJsonCodec: Codec[SafeUUID] = new Codec[SafeUUID] {
        override def apply(a: SafeUUID): Json = Json.fromString(a.safeValue)
        override def apply(c: HCursor): Result[SafeUUID] =
            c.as[String].flatMap { SafeUUID(_).leftMap { s => DecodingFailure(s, Nil) } }
    }
}
