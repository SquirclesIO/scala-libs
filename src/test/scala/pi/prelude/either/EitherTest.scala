package pi.prelude.either

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.either.\/._
import zio.prelude.Validation.Success

class EitherTest extends AnyFunSuite with should.Matchers {
	test("test") {
		val r = Success("toto")
			.bimap( _: Any => "titi", _ => "toto" )
			.fold(identity, identity)

		import zio.prelude._

		"toto".right[String]
			//.bimap(_ => "titi", _ => "toto")
			.map { s => s"zozo: $s" }
			//.flatMap { s => s"titi $s".right[String] }

	}
}
