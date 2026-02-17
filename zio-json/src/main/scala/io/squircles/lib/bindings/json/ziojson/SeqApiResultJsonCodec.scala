package io.squircles.lib.bindings.json.ziojson

import io.squircles.lib.http.SeqApiResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

import scala.annotation.unused

object SeqApiResultJsonCodec {
    import RestNavigationLinkJsonCodec.{restNavigationLinkJsonDecoder, restNavigationLinkJsonEncoder}

    implicit def seqApiResultJsonDecoder[A](implicit @unused jsonDecoder: JsonDecoder[A]): JsonDecoder[SeqApiResult[A]] =
        DeriveJsonDecoder.gen[SeqApiResult[A]]
    implicit def seqApiResultJsonEncoder[A](implicit @unused jsonEncoder: JsonEncoder[A]): JsonEncoder[SeqApiResult[A]] =
        DeriveJsonEncoder.gen[SeqApiResult[A]]
}
