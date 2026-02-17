package pi.prelude.bindings.json.ziojson

import io.github.iltotore.iron.zioJson
import pi.prelude.number.{Percentage, PercentageConstraint}
import zio.json.{JsonDecoder, JsonEncoder}

object PercentageJsonCodec:
    given JsonDecoder[Percentage] = zioJson.given_JsonDecoder_:|[Double, PercentageConstraint]
    given JsonEncoder[Percentage] = zioJson.given_JsonEncoder_:|[Double, PercentageConstraint]
