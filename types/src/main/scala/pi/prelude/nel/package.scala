package pi.prelude

import zio.prelude.NonEmptyList

package object nel {
    type NEL[+A] = NonEmptyList[A]

    def nel[A](x: A, xs: A*): NonEmptyList[A] = NonEmptyList(x, xs: _*)

    implicit class NELOps[A](xs: Seq[A]) {
        def toNEL: Option[NonEmptyList[A]] = xs.headOption.map { h => nel[A](h, xs.tail: _*) }
    }
}
