package pi.prelude.base

package object dsl {
	implicit class ToOptionOps[A](x: A) {
		def some: Option[A] = Some(x)
	}

	implicit class ToEitherOps[A](x: A) {
		def right: Either[Nothing, A] = Right(x)
		def left: Either[A, Nothing] = Left(x)
	}
}