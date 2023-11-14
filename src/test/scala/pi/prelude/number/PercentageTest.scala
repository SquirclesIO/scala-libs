package pi.prelude.number

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import scala.annotation.nowarn

@nowarn("msg=discarded non-Unit value")
class PercentageTest extends AnyFunSuite with should.Matchers {
    test("percentage should have value between 0 & 100") {
        Percentage(12).map { _.value } shouldBe Right(12d)
        Percentage(0).map { _.value } shouldBe Right(0d)
        Percentage(100).map { _.value } shouldBe Right(100d)
        Percentage(101) shouldBe Left(
            "Right predicate of (!(101.0 < 0.0) && !(101.0 > 100.0)) failed: Predicate (101.0 > 100.0) did not fail."
        )
        Percentage(-1) shouldBe Left("Left predicate of (!(-1.0 < 0.0) && !(-1.0 > 100.0)) failed: Predicate (-1.0 < 0.0) did not fail.")
    }
}
