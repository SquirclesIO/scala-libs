package io.squircles.lib.http

import io.squircles.lib.number.{NonNegativeInt, PositiveInt}

/** Représente un range d'API Http
  * @param offset
  *   le pointeur de départ dans la liste
  * @param limit
  *   le nombre limite à récupérer
  */
case class Range(offset: NonNegativeInt, limit: PositiveInt)
