package pi.prelude.http

import pi.prelude.number.PositiveInt

/**
 * Représente un range d'API Http
 * @param offset le pointeur de départ dans la liste
 * @param limit le nombre limite à récupérer
 */
case class Range(offset: PositiveInt, limit: PositiveInt)
