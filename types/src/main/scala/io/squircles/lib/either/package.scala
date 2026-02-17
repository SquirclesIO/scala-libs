package io.squircles.lib.either

import io.squircles.lib.either.either.\/
import io.squircles.lib.either.either.\/._
import zio.ZIO
import zio.prelude.{Bicovariant, Covariant}

package object either {

    object \/ {
        final case class -\/[+E](e: E) extends (E \/ Nothing)
        final case class \/-[+A](x: A) extends (Nothing \/ A)

        def fromEither[E, A](e: Either[E, A]): E \/ A = e match {
            case Left(value)  => -\/(value)
            case Right(value) => \/-(value)
        }

        def fromOption[E, A](ifNone: => E)(o: Option[A]): E \/ A = o.map(\/-(_)).getOrElse(-\/(ifNone))

        implicit def EitherBicovariant: Bicovariant[\/] = new Bicovariant[\/] {
            override def bimap[A, B, AA, BB](f: A => AA, g: B => BB): A \/ B => AA \/ BB =
                _.mapError0(f).map0(g)
        }

        implicit def EitherCovariant[E]: Covariant[({ type lambda[+x] = \/[E, x] })#lambda] =
            new Covariant[({ type lambda[+x] = \/[E, x] })#lambda] {
                override def map[A, B](f: A => B): E \/ A => E \/ B = _.map0(f)
            }

        /*
		implicit def ValidationFailureCovariant[A]
    : Covariant[({ type lambda[+x] = newtypes.Failure[Validation[x, A]] })#lambda] =
    new Covariant[({ type lambda[+x] = newtypes.Failure[Validation[x, A]] })#lambda] {
      def map[E, E1](f: E => E1): newtypes.Failure[Validation[E, A]] => newtypes.Failure[Validation[E1, A]] =
        validation => newtypes.Failure.wrap(newtypes.Failure.unwrap(validation).mapError(f))
    }
         */

        implicit class ZEitherOps[A](private val x: A) extends AnyVal {
            def left[B]: A \/ B = -\/(x)
            def right[B]: B \/ A = \/-(x)
        }

        implicit class EitherToZIOOps[E, A](private val x: E \/ A) extends AnyVal {
            def io: ZIO[Any, E, A] = ZIO.fromEither(x.toEither)
        }

        implicit class ZIOAbsolveOps[R, E, A](private val z: ZIO[R, E, E \/ A]) extends AnyVal {
            def absolve: ZIO[R, E, A] = z.map(_.toEither).absolve
        }
    }
}

sealed trait \/[+E, +A] {
    self =>
    private[either] final def map0[B](f: A => B): \/[E, B] =
        self match {
            case \/-(a)         => \/-(f(a))
            case error @ -\/(_) => error
        }

    private[either] final def mapError0[F](f: E => F): \/[F, A] =
        self match {
            case -\/(e)           => -\/(f(e))
            case success @ \/-(_) => success
        }

    def toEither: Either[E, A] = self match {
        case \/.-\/(e) => Left(e)
        case \/.\/-(x) => Right(x)
    }
}
