package io.squircles.lib.postgres

import cats.implicits.catsSyntaxApplicativeId
import doobie.postgres.implicits.toPostgresMonadErrorOps
import doobie.syntax.all._
import doobie.{ConnectionIO, Fragment, Read}
import io.squircles.lib.postgres.DbError.DbException
import zio.IO
import zio.interop.catz._

extension [T](self: ConnectionIO[T]) {
    def execute(implicit connection: JdbcConnection.Transactor): IO[DbError, T] = self
        .transact(connection)
        .mapError { DbException.apply }
}

extension (self: Fragment) {
    def selectSeq[T: Read](implicit connection: JdbcConnection.Transactor): IO[DbError, Seq[T]] =
        self
            .query[T]
            .to[Seq]
            .execute

    def insertHandlingConflict[E](handler: => E)(implicit connection: JdbcConnection.Transactor): IO[DbError | E, Int] =
        self
            .update
            .run
            .map(Right[DbError | E, Int].apply)
            .onUniqueViolation { Left(handler).pure[ConnectionIO] }
            .transact(connection)
            .mapError(DbException.apply)
            .absolve

    def selectOpt[T: Read](implicit connection: JdbcConnection.Transactor): IO[DbError, Option[T]] =
        self
            .query[T]
            .option
            .execute

    def selectUnique[T: Read](implicit connection: JdbcConnection.Transactor): IO[DbError, T] =
        self
            .query[T]
            .unique
            .execute

    def executeUpdate(implicit connection: JdbcConnection.Transactor): IO[DbError, Int] =
        self
            .update
            .run
            .execute
}

extension [A](self: fs2.Stream[ConnectionIO, A]) {
    def executeUpdate(implicit connection: JdbcConnection.Transactor): IO[DbError, List[A]] = self
        .transact(connection)
        .compile
        .toList
        .mapError(DbException.apply)
}
