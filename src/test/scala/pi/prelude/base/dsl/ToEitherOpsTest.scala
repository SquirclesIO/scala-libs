package pi.prelude.base.dsl

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import scala.annotation.nowarn

@nowarn("msg=discarded non-Unit value")
class ToEitherOpsTest extends AnyFunSuite with should.Matchers {
    test("could use .right suffix on any value") {
        "toto".right shouldBe Right("toto")
        1.right shouldBe Right(1)
        List(1).right shouldBe Right(List(1))
    }

    test("could use .left suffix on any value") {
        "toto".left shouldBe Left("toto")
        1.left shouldBe Left(1)
        List(1).left shouldBe Left(List(1))
    }
}
