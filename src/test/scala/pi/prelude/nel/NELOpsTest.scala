package pi.prelude.nel

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import scala.collection.compat.immutable.ArraySeq

class NELOpsTest extends AnyFunSuite with should.Matchers {
    test("toNEL function") {
        List(1).toNEL shouldBe Some(nel(1)) : Unit
        ArraySeq(1).toNEL shouldBe Some(nel(1)) : Unit

        Nil.toNEL shouldBe None : Unit
        ArraySeq.empty.toNEL shouldBe None
    }
}
