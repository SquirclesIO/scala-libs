package pi.prelude.bindings.json.ziojson

import pi.prelude.http.HttpVerb
import zio.json.{JsonDecoder, JsonEncoder}

object HttpVerbJsonCodec {
    implicit val httpVerbJsonDecoder: JsonDecoder[HttpVerb] = JsonDecoder[String].mapOrFail { v =>
        v match {
            case "GET"     => Right(HttpVerb.GET)
            case "HEAD"    => Right(HttpVerb.HEAD)
            case "POST"    => Right(HttpVerb.POST)
            case "PUT"     => Right(HttpVerb.PUT)
            case "DELETE"  => Right(HttpVerb.DELETE)
            case "OPTIONS" => Right(HttpVerb.OPTIONS)
            case "PATCH"   => Right(HttpVerb.PATCH)
            case "CONNECT" => Right(HttpVerb.CONNECT)
            case "TRACE"   => Right(HttpVerb.TRACE)
            case _         => Left(s"$v is not a valid HttpVerb")
        }
    }

    implicit val httpVerbJsonEncoder: JsonEncoder[HttpVerb] = JsonEncoder[String].contramap { verb =>
        verb match {
            case HttpVerb.GET     => "GET"
            case HttpVerb.HEAD    => "HEAD"
            case HttpVerb.POST    => "POST"
            case HttpVerb.PUT     => "PUT"
            case HttpVerb.DELETE  => "DELETE"
            case HttpVerb.OPTIONS => "OPTIONS"
            case HttpVerb.PATCH   => "PATCH"
            case HttpVerb.CONNECT => "CONNECT"
            case HttpVerb.TRACE   => "TRACE"
        }
    }
}
