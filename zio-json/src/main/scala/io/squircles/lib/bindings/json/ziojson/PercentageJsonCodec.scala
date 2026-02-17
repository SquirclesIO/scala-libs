package io.squircles.lib.bindings.json.ziojson

import io.github.iltotore.iron.zioJson
import io.squircles.lib.number.{Percentage, PercentageConstraint}
import zio.json.{JsonDecoder, JsonEncoder}

object PercentageJsonCodec:
    given JsonDecoder[Percentage] = zioJson.given_JsonDecoder_:|[Double, PercentageConstraint]
    given JsonEncoder[Percentage] = zioJson.given_JsonEncoder_:|[Double, PercentageConstraint]
