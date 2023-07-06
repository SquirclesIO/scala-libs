package pi.prelude.collection

import pi.prelude.nel.NEL
import zio.prelude.NonEmptySet

package object nonemptyset {
	type NES[A] = NonEmptySet[A]

	def nes[A](x: A, xs: A*): NonEmptySet[A] = NonEmptySet(x, xs: _*)

	implicit class FromSeq_NESOps[A](xs: Seq[A]) {
		def toNES: Option[NonEmptySet[A]] = xs.headOption.map { h => nes[A](h, xs.tail: _*) }
	}

	implicit class FromNEL_NESOps[A](xs: NEL[A]) {
		def toNES: NES[A] = NonEmptySet.fromNonEmptyList(xs)
	}
}