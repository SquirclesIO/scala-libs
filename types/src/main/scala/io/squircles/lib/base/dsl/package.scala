package io.squircles.lib.base.dsl

import zio.prelude.Validation

extension [A](x: A)
    def right: Either[Nothing, A] = Right(x)
    def left: Either[A, Nothing] = Left(x)
    def toSeq: Seq[A] = Seq(x)
    def option: Option[A] = Option(x)
    def some: Option[A] = Some(x)

extension (x: Boolean)
    def toEither[E](ifFalse: Either[E, Unit]): Either[E, Unit] =
        if x then Right(())
        else ifFalse

extension [A](a: A) def |>[B](f: A => B): B = f(a)

extension [A](x: A) {
    def vsucceed: Validation[Nothing, A] = Validation.succeed(x)
    def vfail: Validation[A, Nothing] = Validation.fail(x)
}
