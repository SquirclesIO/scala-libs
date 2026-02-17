package pi.prelude.number

import zio.test.{assertTrue, ZIOSpecDefault}

object PercentageTest extends ZIOSpecDefault {
    val spec = suite("Percentage")(
        test("percentage should have value between 0 & 100") {
            assertTrue(Percentage(12).map { _.value } == Right(12d)) &&
            assertTrue(Percentage(0).map { _.value } == Right(0d)) &&
            assertTrue(Percentage(100).map { _.value } == Right(100d)) &&
            assertTrue(Percentage(101) == Left("Should be included in [0.0, 100.0]")) &&
            assertTrue(Percentage(-1) == Left("Should be included in [0.0, 100.0]"))
        }
    )
}
