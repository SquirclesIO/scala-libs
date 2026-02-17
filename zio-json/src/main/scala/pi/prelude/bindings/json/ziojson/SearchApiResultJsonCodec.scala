package pi.prelude.bindings.json.ziojson

import pi.prelude.http.SearchApiResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

import scala.annotation.unused

object SearchApiResultJsonCodec {
    import RestNavigationLinkJsonCodec.{restNavigationLinkJsonDecoder, restNavigationLinkJsonEncoder}

    implicit def searchApiResultJsonEncoder[A, B](implicit
        @unused jsonEncoderA: JsonEncoder[A],
        @unused jsonEncoderB: JsonEncoder[B]): JsonEncoder[SearchApiResult[A, B]] =
        DeriveJsonEncoder.gen[SearchApiResult[A, B]]
    implicit def searchApiResultJsonDecoder[A, B](implicit
        @unused jsonDecoderA: JsonDecoder[A],
        @unused jsonDecoderB: JsonDecoder[B]): JsonDecoder[SearchApiResult[A, B]] =
        DeriveJsonDecoder.gen[SearchApiResult[A, B]]
}
