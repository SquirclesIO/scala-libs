package io.squircles.lib.collection

import scala.util.Try

extension [A](i: Iterable[A]) {

    /** a tail method that fucking doesn't throw Exception
      * @return
      */
    def safeTail: Iterable[A] = Try(i.tail).getOrElse(Nil)
}
