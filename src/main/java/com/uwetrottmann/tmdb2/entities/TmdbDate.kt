package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.TmdbConstants.TMDB_DATE_FORMATTER
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeParseException


class TmdbDate(val localDate: LocalDate?) {

    constructor(date: String) : this(
            try {
                TMDB_DATE_FORMATTER.parse(date) as LocalDate
            } catch (e: DateTimeParseException) {
                null
            })

    override fun toString(): String = TMDB_DATE_FORMATTER.format(localDate)
}
