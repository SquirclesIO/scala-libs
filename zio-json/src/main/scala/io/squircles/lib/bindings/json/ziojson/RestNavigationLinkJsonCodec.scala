package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.http.RestNavigationLink
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object RestNavigationLinkJsonCodec {
    import HttpVerbJsonCodec.{httpVerbJsonDecoder, httpVerbJsonEncoder}

    given restNavigationLinkJsonDecoder: JsonDecoder[RestNavigationLink] = DeriveJsonDecoder.gen[RestNavigationLink]
    given restNavigationLinkJsonEncoder: JsonEncoder[RestNavigationLink] = DeriveJsonEncoder.gen[RestNavigationLink]
}
