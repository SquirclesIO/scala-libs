package pi.prelude.safeio

import zio.ZIO

object OptionToZioLeft {
    implicit class OptionToZioLeft[A, E1, E2](z: ZIO[Any, E1, Option[A]]) {
        def optionToLeft(e: E1 => E2, r: E2): ZIO[Any, E2, A] =
            z.mapError[E2] { e(_) }
                .map { _.toRight(r) }
                .absolve
    }
}
