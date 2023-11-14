package pi.prelude.base.dsl

import zio.prelude.Validation
import zio.test.{assertTrue, ZIOSpecDefault}

object ToValidationOps extends ZIOSpecDefault {
    val spec = suite("ToValidationOps")(
        test("could use .vsucceed suffix on any value") {
            assertTrue("toto".vsucceed == Validation.succeed("toto")) &&
            assertTrue(1.vsucceed == Validation.succeed(1)) &&
            assertTrue(List(1).vsucceed == Validation.succeed(List(1))) &&
            assertTrue(None.vsucceed == Validation.succeed(None)) &&
            assertTrue(Some(1).vsucceed == Validation.succeed(Some(1)))
        },
        test("could use .vfail suffix on any value") {
            assertTrue("toto".vfail == Validation.fail("toto")) &&
            assertTrue(1.vfail == Validation.fail(1)) &&
            assertTrue(List(1).vfail == Validation.fail(List(1)))
        }
    )

}
