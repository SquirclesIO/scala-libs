package io.squircles.lib.number

import zio.test.{assertTrue, ZIOSpecDefault}

import NonNegativeInt.value

object NonNegativeIntTest extends ZIOSpecDefault {
    val spec = suite("NonNegativeInt")(
        test("non negative Int is always positive") {
            assertTrue(NonNegativeInt.either(0).map { _.value } == Right(0)) &&
            assertTrue(NonNegativeInt.either(42).map { _.value } == Right(42)) &&
            assertTrue(NonNegativeInt.either(-1) == Left("Should be positive or zero"))
        }
    )
}
