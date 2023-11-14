package pi.prelude.bindings.json.ziojson

import zio.json.internal.RetractReader
import zio.json.{JsonDecoder, JsonError}

object UnitJsonCodec {

    /** Un body qui ne contient rien (pour des réponses en 204 par exemple)
      */
    implicit val emptyJsonDecoder: JsonDecoder[Unit] = (_: List[JsonError], _: RetractReader) => ()
}
