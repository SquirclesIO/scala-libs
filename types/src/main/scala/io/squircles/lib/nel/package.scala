package io.squircles.lib.nel

import io.squircles.lib.number.PositiveInt
import zio.prelude.NonEmptyList

import scala.collection.View

type NEL[+A] = NonEmptyList[A]

def nel[A](x: A, xs: A*): NonEmptyList[A] = NonEmptyList(x, xs*)

extension [A](xs: Seq[A]) def toNEL: Option[NonEmptyList[A]] = NonEmptyList.fromIterableOption(xs)

def iterate[A](initial: A, count: PositiveInt)(next: A => A): NonEmptyList[A] =
    NonEmptyList.fromIterable(initial, View.iterate(next(initial), count - 1)(next))
