package pi.prelude.string

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

class PrintClassNameOpsTest extends AnyFunSuite with should.Matchers with PrintClassNameOps {
	test("should show name of class") {
		clazzStringRepr(new Juju(0)) shouldBe "Juju"
	}

	test("should show name of case class") {
		clazzStringRepr(Toto("titi")) shouldBe "Toto"
	}

	test("should show case object's name") {
		clazzStringRepr(Tutu) shouldBe "Tutu"
	}

	test("should show Object's name") {
		clazzStringRepr(Popo) shouldBe "Popo"
	}

	class Juju(i: Int)
	case class Toto(s: String)
	case object Tutu
	object Popo
}
