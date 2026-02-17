package pi.prelude.number

import zio.test.{ZIOSpecDefault, assertTrue}

object PositiveDoubleTest extends ZIOSpecDefault {
    val spec = suite("PositiveDouble") (
    test("a positive double should be >= 0") {
        assertTrue(PositiveDouble(0).map { _.toDouble } == Right(0d)) &&
        assertTrue(PositiveDouble(145.34).map { _.toDouble } == Right(145.34)) &&
        assertTrue(PositiveDouble(-145.34).map { _.toDouble } == Left("Should be positive or zero"))
    }
    )

}
