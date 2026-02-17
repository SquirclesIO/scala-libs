package io.squircles.lib.bindings.json.circe

import io.circe.CursorOp.DownArray
import io.circe.Decoder.Result
import io.circe.syntax._
import io.circe.{Codec, Decoder, DecodingFailure, Encoder, HCursor, Json}
import zio.prelude.NonEmptyList

object NonEmptyListJsonCodec {
    implicit def nonemptylistJsonCodec[A: Encoder: Decoder]: Codec[NonEmptyList[A]] = new Codec[NonEmptyList[A]] {
        override def apply(c: HCursor): Result[NonEmptyList[A]] =
            c.as[List[A]].flatMap { list =>
                NonEmptyList
                    .fromIterableOption(list)
                    .toRight(DecodingFailure("list should be non empty", List(DownArray)))
            }

        override def apply(a: NonEmptyList[A]): Json = a.toList.asJson
    }
}
