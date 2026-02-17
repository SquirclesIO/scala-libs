package pi.prelude.safeuuid

import pi.prelude.safeuuid.SafeUUID.safeUuidEqual
import zio.prelude._
import zio.test.Assertion.{isFalse, isTrue}
import zio.test.{assert, assertTrue, ZIOSpecDefault}

import java.util.UUID

object SafeUUIDTest extends ZIOSpecDefault {
    val randomUUID = UUID.randomUUID()

    val spec = suite("SafeUUID")(
        test("check if safe UUID is used") {
            val correctUid = randomUUID.toString
            val unsafeUid = "toto"

            assertTrue(SafeUUID(correctUid).map(_.safeValue) == Right(correctUid)) &&
            assertTrue(SafeUUID(unsafeUid) == Left(s"[$unsafeUid] has not the good format (need SHA-256 String format)"))
        },
        test("build direct SafeUUID from java.util.UUID") {
            assertTrue(SafeUUID(randomUUID).safeValue == randomUUID.toString) &&
            assertTrue(SafeUUID(UUID.fromString(randomUUID.toString)).safeValue == randomUUID.toString)
        },
        test("test equality") {
            val safe1 = SafeUUID(randomUUID)
            val safe2 = SafeUUID(randomUUID)
            val safe3 = SafeUUID.generate

            assert(safe1 == safe2)(isTrue) &&
            assert(safe1 == safe2)(isTrue) &&
            assert(safe1 == safe3)(isFalse) &&
            assert(safe1 != safe3)(isTrue) &&
            assert(safe1 === safe2)(isTrue) &&
            assert(safe1 === safe3)(isFalse) &&
            assert(safe1 != safe3)(isTrue) &&
            assert(safe1 != safe2)(isFalse)
        }
    )
}
