package io.squircles.lib.number

import zio.test.{assertTrue, ZIOSpecDefault}

import PositiveDouble.value

object PositiveDoubleTest extends ZIOSpecDefault {
    val spec = suite("PositiveDouble")(
        test("a positive double should be >= 0") {
            assertTrue(PositiveDouble.either(0).map { _.value } == Right(0d)) &&
            assertTrue(PositiveDouble.either(145.34).map { _.value } == Right(145.34)) &&
            assertTrue(PositiveDouble.either(-145.34).map { _.value } == Left("Should be positive or zero"))
        }
    )

}
