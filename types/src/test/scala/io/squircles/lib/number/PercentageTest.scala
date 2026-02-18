package io.squircles.lib.number

import Percentage.value
import zio.test.{ZIOSpecDefault, assertTrue}

object PercentageTest extends ZIOSpecDefault {
    val spec = suite("Percentage")(
        test("percentage should have value between 0 & 100") {
            assertTrue(Percentage.either(12).map { _.value } == Right(12d)) &&
            assertTrue(Percentage.either(0).map { _.value } == Right(0d)) &&
            assertTrue(Percentage.either(100).map { _.value } == Right(100d)) &&
            assertTrue(Percentage.either(101) == Left("Should be included in [0.0, 100.0]")) &&
            assertTrue(Percentage.either(-1) == Left("Should be included in [0.0, 100.0]"))
        }
    )
}
