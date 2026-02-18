package io.squircles.lib.number

import io.github.iltotore.iron.constraint.numeric.Interval.Closed
import io.github.iltotore.iron.constraint.numeric.{Positive, Positive0}
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
    def either(v: Double): Either[String, Percentage] = v.refineEither
    extension (self: Percentage) def value: Double = self

type PositiveDouble = Double :| Positive0

object PositiveDouble:
    def either(d: Double): Either[String, PositiveDouble] = d.refineEither
    extension (self: PositiveDouble) def value: Double = self

type PositiveInt = Int :| Positive

object PositiveInt:
    def either(v: Int): Either[String, PositiveInt] = v.refineEither
    extension (self: PositiveInt) def value: Int = self

type NonNegativeInt = Int :| Positive0

object NonNegativeInt:
    def either(v: Int): Either[String, NonNegativeInt] = v.refineEither
    extension (self: NonNegativeInt) def value: Int = self
