package pi.prelude

package object boolean {
	implicit class BooleanOps(b: Boolean) {
		def toEither[E](left: E): Either[E, Unit] = if(b) Right(()) else Left(left)
	}
}