package pi.prelude.number

import io.github.iltotore.iron.constraint.numeric.Interval.Closed
import io.github.iltotore.iron.constraint.numeric.Positive0
import io.github.iltotore.iron.{:|, refineEither}

/** Number between 0 & 100
  *
  * val p: Percentage = ???
  *
  * val res: Double = p.value
  */
type Percentage = Double :| PercentageConstraint

type PercentageConstraint = Closed[0d, 100d]

object Percentage:
    def apply(v: Double): Either[String, Percentage] = v.refineEither

extension (self: Percentage) def value: Double = self

type PositiveDouble = Double :| Positive0

object PositiveDouble:
    def apply(d: Double): Either[String, PositiveDouble] = d.refineEither

extension (self: PositiveDouble) def toDouble: Double = self

type PositiveInt = Int :| Positive0

object PositiveInt:
    def eval(v: Int): Either[String, PositiveInt] = v.refineEither

extension (self: PositiveInt) def value: Int = self
