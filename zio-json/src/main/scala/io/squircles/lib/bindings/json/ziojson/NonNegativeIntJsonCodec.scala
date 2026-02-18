package io.squircles.lib.bindings.json.ziojson

import io.github.iltotore.iron.refineEither
import io.squircles.lib.number.NonNegativeInt
import zio.json.{JsonDecoder, JsonEncoder}

object NonNegativeIntJsonCodec:

    given JsonDecoder[NonNegativeInt] = JsonDecoder.int.mapOrFail(_.refineEither)
    given JsonEncoder[NonNegativeInt] = JsonEncoder.int.contramap(identity)
