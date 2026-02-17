package io.squircles.lib.nel

import io.github.iltotore.iron.autoRefine
import io.squircles.lib.number.PositiveInt
import zio.prelude.NonEmptyList
import zio.test.Assertion._
import zio.test._

object NELSpec extends ZIOSpecDefault:
    val initial = 1

    def genPositiveInt(min: PositiveInt, max: PositiveInt): Gen[Any, PositiveInt] =
        Gen.int(min, max)
            .map(PositiveInt.eval)
            .collect { case Right(value) => value }

    val spec = suite("NonEmptyList")(
        suite("iterate")(
            test("size == 1 should return initial value") {
                assert(iterate(initial, 1)(_ + 1))(equalTo(NonEmptyList(1)))
            },
            test("size == 2 should return initial value + next") {
                assert(iterate(initial, 2)(_ + 1))(equalTo(NonEmptyList(1, 2)))
            },
            test("should return a nonemptylist containing the same thing a List.iterate would") {
                check(genPositiveInt(1, 1000)) { size =>
                    val f: Int => Int = _ + 1
                    assert(iterate(initial, size)(f).toList)(equalTo(List.iterate(initial, size)(f)))
                }
            }
        )
    )
