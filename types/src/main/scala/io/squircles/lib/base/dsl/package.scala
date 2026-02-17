package io.squircles.lib.base

import zio.prelude.Validation

package object dsl {
    implicit class ToOptionOps[A](x: A) {
        def some: Option[A] = Some(x)
    }

    implicit class ToEitherOps[A](x: A) {
        def right: Either[Nothing, A] = Right(x)
        def left: Either[A, Nothing] = Left(x)
    }

    implicit class ValidationOps[A](x: A) {
        def vsucceed: Validation[Nothing, A] = Validation.succeed(x)
        def vfail: Validation[A, Nothing] = Validation.fail(x)
    }
}
