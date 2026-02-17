package io.squircles.lib

import scala.util.Try

package object collection {

    implicit class IterableOps[+A](i: Iterable[A]) {

        /** a tail method that fucking doesn't throw Exception
          * @return
          */
        def safeTail: Iterable[A] = Try(i.tail).getOrElse(Nil)
    }

}
