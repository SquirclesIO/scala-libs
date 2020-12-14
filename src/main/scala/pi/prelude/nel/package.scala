package pi.prelude

import zio.prelude.NonEmptyList

package object nel {
	type NEL[+A] = NonEmptyList[A]

	def nel[A](x: A, xs: A*): NonEmptyList[A] = NonEmptyList(x, xs:_*)
}