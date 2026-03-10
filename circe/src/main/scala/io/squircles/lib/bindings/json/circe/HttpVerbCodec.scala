package io.squircles.lib.bindings.json.circe

import io.circe.{Decoder, Encoder}
import io.squircles.lib.http.HttpVerb

object HttpVerbCodec {
    given decoder: Decoder[HttpVerb] = Decoder.decodeString.emap {
        case "GET"     => Right(HttpVerb.GET)
        case "HEAD"    => Right(HttpVerb.HEAD)
        case "POST"    => Right(HttpVerb.POST)
        case "PUT"     => Right(HttpVerb.PUT)
        case "DELETE"  => Right(HttpVerb.DELETE)
        case "OPTIONS" => Right(HttpVerb.OPTIONS)
        case "PATCH"   => Right(HttpVerb.PATCH)
        case "CONNECT" => Right(HttpVerb.CONNECT)
        case "TRACE"   => Right(HttpVerb.TRACE)
        case other     => Left(s"$other n'est pas un verbe HttpVerb supporté")
    }

    given encoder: Encoder[HttpVerb] = Encoder.encodeString.contramap {
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
