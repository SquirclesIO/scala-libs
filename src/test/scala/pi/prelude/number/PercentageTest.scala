package pi.prelude.number

import zio.test.{ZIOSpecDefault, assertTrue}

object PercentageTest extends ZIOSpecDefault {
    val spec = suite("Percentage") (
    test("percentage should have value between 0 & 100") {
        assertTrue(Percentage(12).map { _.value } == Right(12d)) &&
        assertTrue(Percentage(0).map { _.value } == Right(0d)) &&
        assertTrue(Percentage(100).map { _.value } == Right(100d)) &&
        assertTrue(Percentage(101) == Left(
            "Right predicate of (!(101.0 < 0.0) && !(101.0 > 100.0)) failed: Predicate (101.0 > 100.0) did not fail."
        )) &&
        assertTrue(Percentage(-1) == Left("Left predicate of (!(-1.0 < 0.0) && !(-1.0 > 100.0)) failed: Predicate (-1.0 < 0.0) did not fail."))
    }
    )
}
