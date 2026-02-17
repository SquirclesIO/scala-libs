package io.squircles.lib.bindings.json.ziojson

import io.github.iltotore.iron.constraint.numeric.Positive0
import io.github.iltotore.iron.zioJson
import io.squircles.lib.number.PositiveInt
import zio.json.{JsonDecoder, JsonEncoder}

object PositiveIntJsonCodec:

    given JsonDecoder[PositiveInt] = zioJson.given_JsonDecoder_:|[Int, Positive0]
    given JsonEncoder[PositiveInt] = zioJson.given_JsonEncoder_:|[Int, Positive0]
