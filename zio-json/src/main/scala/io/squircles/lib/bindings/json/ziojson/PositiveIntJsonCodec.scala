package io.squircles.lib.bindings.json.ziojson

import io.github.iltotore.iron.refineEither
import io.squircles.lib.number.PositiveInt
import zio.json.{JsonDecoder, JsonEncoder}

object PositiveIntJsonCodec:

    given JsonDecoder[PositiveInt] = JsonDecoder.int.mapOrFail(_.refineEither)
    given JsonEncoder[PositiveInt] = JsonEncoder.int.contramap(identity)
