package io.squircles.lib.http

case class SeqApiResult[A](result: Seq[A], _links: Seq[RestNavigationLink])
