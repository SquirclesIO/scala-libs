package io.squircles.lib.number

import zio.test.{assertTrue, ZIOSpecDefault}

import PositiveInt.value

object PositiveIntTest extends ZIOSpecDefault {
    val spec = suite("PositiveInt")(
        test("positive Int is always positive") {
            assertTrue(PositiveInt.either(0) == Left("Should be strictly positive")) &&
            assertTrue(PositiveInt.either(42).map { _.value } == Right(42)) &&
            assertTrue(PositiveInt.either(-1) == Left("Should be strictly positive"))
        }
    )
}
