package pi.prelude.bindings.json.ziojson

import pi.prelude.http.SeqApiResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object SeqApiResultJsonCodec {
	import RestNavigationLinkJsonCodec.{restNavigationLinkJsonDecoder, restNavigationLinkJsonEncoder}

	implicit def seqApiResultJsonDecoder[A: JsonDecoder]: JsonDecoder[SeqApiResult[A]] = DeriveJsonDecoder.gen[SeqApiResult[A]]
	implicit def seqApiResultJsonEncoder[A: JsonEncoder]: JsonEncoder[SeqApiResult[A]] = DeriveJsonEncoder.gen[SeqApiResult[A]]
}