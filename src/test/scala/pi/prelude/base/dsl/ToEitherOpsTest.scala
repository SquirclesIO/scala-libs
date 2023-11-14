package pi.prelude.base.dsl

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class ToEitherOpsTest extends AnyFunSuite with should.Matchers {
    test("could use .right suffix on any value") {
        "toto".right shouldBe Right("toto") : Unit
        1.right shouldBe Right(1) : Unit
        List(1).right shouldBe Right(List(1))
    }

    test("could use .left suffix on any value") {
        "toto".left shouldBe Left("toto") : Unit
        1.left shouldBe Left(1) : Unit
        List(1).left shouldBe Left(List(1))
    }
}
