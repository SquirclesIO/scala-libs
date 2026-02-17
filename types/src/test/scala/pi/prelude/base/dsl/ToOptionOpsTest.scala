package pi.prelude.base.dsl

import zio.test.{assertTrue, ZIOSpecDefault}

object ToOptionOpsTest extends ZIOSpecDefault {
    val spec = suite("ToOptionOps")(
        test("could use .some suffix on any value") {
            assertTrue("toto".some == Some("toto")) &&
            assertTrue(1.some == Some(1)) &&
            assertTrue(List(1).some == Some(List(1)))
        }
    )
}
