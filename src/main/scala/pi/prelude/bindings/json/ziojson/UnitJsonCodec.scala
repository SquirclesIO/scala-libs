package pi.prelude.bindings.json.ziojson

import zio.json.{JsonDecoder, JsonError}
import zio.json.internal.RetractReader

object UnitJsonCodec {
	/**
	 * Un body qui ne contient rien (pour des réponses en 204 par exemple)
	 */
	implicit val emptyJsonDecoder = new JsonDecoder[Unit] {
		override def unsafeDecode(trace: List[JsonError], in: RetractReader): Unit = ()
	}
}