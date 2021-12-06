package pi.prelude.bindings.json.ziojson

import pi.prelude.http.SearchApiResult
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

object SearchApiResultJsonCodec {
	import RestNavigationLinkJsonCodec.{restNavigationLinkJsonEncoder, restNavigationLinkJsonDecoder}

	implicit def searchApiResultJsonEncoder[A: JsonEncoder, B: JsonEncoder]: JsonEncoder[SearchApiResult[A, B]] = DeriveJsonEncoder.gen[SearchApiResult[A, B]]
	implicit def searchApiResultJsonDecoder[A: JsonDecoder, B: JsonDecoder]: JsonDecoder[SearchApiResult[A, B]] = DeriveJsonDecoder.gen[SearchApiResult[A, B]]
}