package pi.prelude.number

import zio.test.{assertTrue, ZIOSpecDefault}

object PositiveIntTest extends ZIOSpecDefault {
    val spec = suite("PositiveInt")(
        test("positive Int is always positive") {
            assertTrue(PositiveInt.eval(0).map { _.value } == Right(0)) &&
            assertTrue(PositiveInt.eval(42).map { _.value } == Right(42)) &&
            assertTrue(PositiveInt.eval(-1) == Left("Predicate (-1 < 0) did not fail."))
        }
    )
}
