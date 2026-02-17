package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.http.RestNavigationLink
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object RestNavigationLinkJsonCodec {
    import HttpVerbJsonCodec.{httpVerbJsonDecoder, httpVerbJsonEncoder}

    implicit val restNavigationLinkJsonDecoder: JsonDecoder[RestNavigationLink] = DeriveJsonDecoder.gen[RestNavigationLink]
    implicit val restNavigationLinkJsonEncoder: JsonEncoder[RestNavigationLink] = DeriveJsonEncoder.gen[RestNavigationLink]
}
