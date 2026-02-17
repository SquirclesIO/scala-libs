package io.squircles.lib

import zio.{IO, UIO, ZIO}

package object safeio {
    // TODO a virer - comment faire du traverse avec zio prelude
    def zioCollectOpt[R, E, A](o: Option[ZIO[R, E, A]]): ZIO[R, E, Option[A]] = {
        ZIO.collectAll(o.toList).map { _.headOption }
    }

    implicit class EitherOps[E, A](e: Either[E, A]) {
        def zio: IO[E, A] = ZIO.fromEither(e)
    }

    implicit class OptionOps[R, E, A](o: Option[ZIO[R, E, A]]) {
        def collectOptZ: ZIO[R, E, Option[A]] = zioCollectOpt(o)
    }

    implicit class ZIOBaseOps[A](x: A) {
        def zsucceed: UIO[A] = ZIO.succeed(x)
    }

    implicit class ZIOOptOps[R, E, +A](zio: ZIO[R, E, Option[A]]) {
        def mapOpt[B](f: A => B): ZIO[R, E, Option[B]] = zio.map { _.map(f) }
        def flatMapOpt[B](f: A => ZIO[R, E, B]): ZIO[R, E, Option[B]] = zio.flatMap { opt => zioCollectOpt(opt.map(f)) }

        def getOrElseOpt[AA >: A](f: AA): ZIO[R, E, AA] = zio.map { _.getOrElse(f) }
    }

    implicit class ZIOSeqOps[R, E, +A](zio: ZIO[R, E, Seq[A]]) {
        def mapSeq[B](f: A => B): ZIO[R, E, Seq[B]] = zio.map { _.map { f } }
    }
}
