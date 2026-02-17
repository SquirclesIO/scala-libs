package pi.prelude.http

case class SearchApiResult[+A, +B](result: Seq[A], aggregations: B, _links: Seq[RestNavigationLink])
