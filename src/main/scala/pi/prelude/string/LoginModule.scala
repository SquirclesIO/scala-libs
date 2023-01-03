package pi.prelude.string

import zio.prelude.Subtype

private[string] trait LoginModule {
	private [LoginModule] object LoginT extends Subtype[String]
	type Login = LoginT.Type

	object Login {
		def apply(raw: String): Either[String, Login] = {
			val trimmed = raw.trim
			if(trimmed.isBlank) Left("Should not be empty")
			else Right(LoginT.wrap(trimmed))
		}
	}
}