package pi.prelude

import zio.ZIO

package object safeio {
	// TODO a virer - comment faire du traverse avec zio prelude
	def zioCollectOpt[R, E, A](o: Option[ZIO[R, E, A]]): ZIO[R, E, Option[A]] = {
		ZIO.collectAll(o.toList).map { _.headOption }
	}
}