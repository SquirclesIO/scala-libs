package io.squircles.lib.string

import io.github.iltotore.iron.constraint.all.{Empty, Not}
import io.github.iltotore.iron.{:|, refineUnsafe}

private[string] trait NonEmptyString {

    extension (self: String :| Not[Empty]) {
        def append(other: String): String :| Not[Empty] =
            (self + other).refineUnsafe[Not[Empty]]
    }

}
