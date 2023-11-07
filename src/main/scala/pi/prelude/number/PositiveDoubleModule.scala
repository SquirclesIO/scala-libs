package pi.prelude.number

import zio.prelude.Newtype

private[number] trait PositiveDoubleModule {
	private [PositiveDoubleModule] object PositiveDoubleT extends Newtype[Double] {
		override def wrap(d: Double): PositiveDouble = super.wrap(d)
	}

	type PositiveDouble = PositiveDoubleT.Type

	object PositiveDouble {
		def apply(d: Double): Either[String, PositiveDouble] = {
			if(d >= 0) Right(PositiveDoubleT.wrap(d))
			else Left(s"$d is not >= 0")
		}
	}

	implicit class PositiveDoubleOps(private val d: PositiveDouble) {
		def toDouble: Double = PositiveDoubleT.unwrap(d)
	}
}