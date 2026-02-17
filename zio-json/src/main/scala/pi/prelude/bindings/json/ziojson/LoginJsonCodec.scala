package pi.prelude.bindings.json.ziojson

import pi.prelude.string.Login
import zio.json.{JsonDecoder, JsonEncoder}

object LoginJsonCodec {
    implicit val loginJsonDecoder: JsonDecoder[Login] = JsonDecoder[String].mapOrFail { Login.apply }
    implicit val loginJsonEncoder: JsonEncoder[Login] = JsonEncoder[String].contramap { identity(_) }
}
