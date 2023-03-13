package pi.prelude.safeio

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import pi.prelude.safeio.Toto.{Titi, Tutu}
import zio.ZIO

class SafeIOTest extends AnyFunSuite with should.Matchers {
	test("ZIOOptOps compile test - getOrElseOpt for enum") {
		ZIO.foreach(Some("Yo")) { x => ZIO.succeed(Tutu(x)) }
			.getOrElseOpt(Titi)
	}

	test("ZIOOptOps compile test - mapOpt for enum") {
		ZIO.succeed(Some(""))
			.mapOpt { Tutu }
			.getOrElseOpt(Titi)
	}
}

sealed trait Toto
object Toto {
	case object Titi extends Toto
	case class Tutu(x: String) extends Toto
	case class Tata(x: String) extends Toto
}