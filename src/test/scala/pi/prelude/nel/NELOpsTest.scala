package pi.prelude.nel

import zio.test.{ZIOSpecDefault, assertTrue}

import scala.collection.compat.immutable.ArraySeq

object NELOpsTest extends ZIOSpecDefault {
    val spec = suite("NELOps")(
        test("toNEL function") {
            assertTrue(List(1).toNEL == Some(nel(1))) &&
            assertTrue(ArraySeq(1).toNEL == Some(nel(1))) &&
            assertTrue(Nil.toNEL == None) &&
            assertTrue(ArraySeq.empty[Int].toNEL == None)
        }
    )
}
