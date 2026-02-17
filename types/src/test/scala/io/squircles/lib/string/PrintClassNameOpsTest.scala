package io.squircles.lib.string

import zio.test.{assertTrue, ZIOSpecDefault}

import scala.annotation.unused

object PrintClassNameOpsTest extends ZIOSpecDefault {
    val spec = suite("PrintClassNameOps")(
        test("should show name of class") {
            assertTrue(clazzStringRepr(new Juju(0)) == "Juju")
        },
        test("should show name of case class") {
            assertTrue(clazzStringRepr(Toto("titi")) == "Toto")
        },
        test("should show case object's name") {
            assertTrue(clazzStringRepr(Tutu) == "Tutu")
        },
        test("should show Object's name") {
            assertTrue(clazzStringRepr(Popo) == "Popo")
        }
    )

    class Juju(@unused i: Int)
    case class Toto(s: String)
    case object Tutu
    object Popo
}
