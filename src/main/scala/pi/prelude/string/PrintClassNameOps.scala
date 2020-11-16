package pi.prelude.string

private [string] trait PrintClassNameOps {
	/**
	 * value.getClass.getSimpleName (avec le pattern sealed trait Toto - object Toto), lève une exception
	 */
	def clazzStringRepr[A](value: A): String = {
		try {
			value.getClass.getSimpleName.replaceAll("\\$", "")
		} catch {
			case _: Throwable => value.getClass.getName.split("\\$").last
		}
	}
}