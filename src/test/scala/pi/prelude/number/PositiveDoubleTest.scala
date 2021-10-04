package pi.prelude.number

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class PositiveDoubleTest extends AnyFunSuite with should.Matchers {
	test("a positive double should be >= 0") {
		PositiveDouble(0).map { _.toDouble } shouldBe Right(0D)
		PositiveDouble(145.34).map { _.toDouble } shouldBe Right(145.34)
		PositiveDouble(-145.34).map { _.toDouble } shouldBe Left(s"-145.34 is not >= 0")
	}
}
