package pi.prelude.base.dsl

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class ToOptionOpsTest extends AnyFunSuite with should.Matchers {
    test("could use .some suffix on any value") {
        "toto".some shouldBe Some("toto")
        1.some shouldBe Some(1)
        List(1).some shouldBe Some(List(1))
    }
}
