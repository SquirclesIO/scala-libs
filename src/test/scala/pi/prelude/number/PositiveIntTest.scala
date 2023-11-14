package pi.prelude.number

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class PositiveIntTest extends AnyFunSuite with should.Matchers {
    test("positive Int is always positive") {
        PositiveInt.eval(0).map { _.value } shouldBe Right(0) : Unit
        PositiveInt.eval(42).map { _.value } shouldBe Right(42) : Unit
        PositiveInt.eval(-1) shouldBe Left("Predicate (-1 < 0) did not fail.") : Unit

        PositiveInt.build(0).value shouldBe 0 : Unit
        PositiveInt.build(42).value shouldBe 42
    }
}
