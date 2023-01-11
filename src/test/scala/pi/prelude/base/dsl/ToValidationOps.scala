package pi.prelude.base.dsl

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import zio.prelude.Validation

class ToValidationOps extends AnyFunSuite with should.Matchers {
	test("could use .vsucceed suffix on any value") {
		"toto".vsucceed shouldBe Validation.succeed("toto")
		1.vsucceed shouldBe Validation.succeed(1)
		List(1).vsucceed shouldBe Validation.succeed(List(1))
	}

	test("could use .vfail suffix on any value") {
		"toto".vfail shouldBe Validation.fail("toto")
		1.vfail shouldBe Validation.fail(1)
		List(1).vfail shouldBe Validation.fail(List(1))
	}
}