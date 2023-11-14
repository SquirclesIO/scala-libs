package pi.prelude.number

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.GreaterEqual
import eu.timepit.refined.refineV

private[number] trait PositiveIntModule {

    /** Int >= 0
      *
      * val p: PositiveInt = ??? val res: Int = p.value
      */
    type PositiveInt = Int Refined PositiveIntPredicate

    type PositiveIntPredicate = GreaterEqual[0]

    object PositiveInt {

        /** évaluation au runtime */
        def eval(v: Int): Either[String, PositiveInt] = refineV(v)
    }
}
