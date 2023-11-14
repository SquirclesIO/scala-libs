package pi.prelude.number

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval.Closed
import eu.timepit.refined.refineV

private[number] trait PercentageModule {

    /** Number between 0 & 100
      *
      * val p: Percentage = ??? val res: Double = p.value
      */
    type Percentage = Double Refined PourcentagePredicate

    type PourcentagePredicate = Closed[0d, 100d]

    object Percentage {

        /** runtime verification */
        def apply(v: Double): Either[String, Percentage] = refineV(v)
    }
}
