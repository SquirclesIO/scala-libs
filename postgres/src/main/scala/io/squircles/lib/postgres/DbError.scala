package io.squircles.lib.postgres

enum DbError {
    case ConnectionError(e: Throwable)
    case DbException(e: Throwable)
    case DecodingError(e: String)
}

object DbError {
    def showError(e: DbError): String = e match {
        case ConnectionError(e) => e.getMessage
        case DbException(e)     => e.getMessage
        case DecodingError(e)   => e
    }
}
