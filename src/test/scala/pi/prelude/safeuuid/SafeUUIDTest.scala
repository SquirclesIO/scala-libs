package pi.prelude.safeuuid

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

import java.util.UUID

class SafeUUIDTest extends AnyFunSuite with should.Matchers {
    val randomUUID = UUID.randomUUID()

    test("check if safe UUID is used") {
        val correctUid = randomUUID.toString
        SafeUUID(correctUid).fold(error => fail(error), ok => ok.safeValue shouldBe correctUid) : Unit

        val unsafeUid = "toto"
        SafeUUID(unsafeUid).fold(
            error => error shouldBe s"[$unsafeUid] has not the good format (need SHA-256 String format)",
            _ => fail(s"[${unsafeUid}] should be failure")
        )
    }

    test("build direct SafeUUID from java.util.UUID") {
        SafeUUID(randomUUID).safeValue shouldBe randomUUID.toString : Unit
        SafeUUID(UUID.fromString(randomUUID.toString)).safeValue shouldBe randomUUID.toString
    }

    test("test equality") {
        val safe1 = SafeUUID(randomUUID)
        val safe2 = SafeUUID(randomUUID)
        val safe3 = SafeUUID.generate

        safe1 shouldBe safe2 : Unit
        safe1 == safe2 shouldBe true : Unit
        safe1 == safe3 shouldBe false : Unit
        safe1 != safe3 shouldBe true : Unit

        safe1 === safe2 shouldBe true : Unit
        safe1 === safe3 shouldBe false : Unit
        safe1 != safe3 shouldBe true : Unit
        safe1 != safe2 shouldBe false
    }
}
