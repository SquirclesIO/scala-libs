package io.squircles.lib.bindings.json.circe

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import io.squircles.lib.http.RestNavigationLink

object RestNavigationLinkCodec {

    import HttpVerbCodec.given
    given codec: Codec[RestNavigationLink] = deriveCodec

}
