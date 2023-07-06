package pi.prelude.bindings.json.ziojson

import zio.json.{JsonDecoder, JsonEncoder}
import pi.prelude.collection.nonemptyset._

object NonEmptySetJsonCodec {
	implicit def nesJsonDecoder[A: JsonDecoder]: JsonDecoder[NES[A]] = JsonDecoder[Seq[A]].mapOrFail {_.toNES.toRight("should be nonempty")}
	implicit def nesJsonEncoder[A: JsonEncoder]: JsonEncoder[NES[A]] = JsonEncoder[Seq[A]].contramap {_.toList}
}