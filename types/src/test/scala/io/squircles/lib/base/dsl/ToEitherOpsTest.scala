package io.squircles.lib.base.dsl

import zio.test.{assertTrue, ZIOSpecDefault}

object ToEitherOpsTest extends ZIOSpecDefault {
    val spec = suite("ToEitherOps")(
        test("could use .right suffix on any value") {
            assertTrue("toto".right == Right("toto")) &&
            assertTrue(1.right == Right(1)) &&
            assertTrue(List(1).right == Right(List(1)))
        },
        test("could use .left suffix on any value") {
            assertTrue("toto".left == Left("toto")) &&
            assertTrue(1.left == Left(1)) &&
            assertTrue(List(1).left == Left(List(1)))
        }
    )

}
