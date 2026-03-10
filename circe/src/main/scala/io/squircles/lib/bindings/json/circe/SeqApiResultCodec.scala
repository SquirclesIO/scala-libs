package io.squircles.lib.bindings.json.circe

import io.circe.generic.semiauto.deriveCodec
import io.circe.{Codec, Decoder, Encoder}
import io.squircles.lib.http.SeqApiResult

object SeqApiResultCodec {

    import RestNavigationLinkCodec.given
    given codec[A: Encoder: Decoder]: Codec[SeqApiResult[A]] = deriveCodec

}
