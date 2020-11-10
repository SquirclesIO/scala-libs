package pi.prelude.string

import zio.prelude.Newtype

private [string] trait NoCaseStringModule { // TODO use Subtype instead of Newtype
	private [NoCaseStringModule] object NoCaseStringT extends Newtype[String]

	type NoCaseString = NoCaseStringT.Type

	object NoCaseString {
		def apply(s: String): NoCaseString = NoCaseStringT.wrap(s.toLowerCase)
	}

	implicit class NoCaseStringOps(private val s: NoCaseString) {
		def string: String = NoCaseStringT.unwrap(s)
	}
}