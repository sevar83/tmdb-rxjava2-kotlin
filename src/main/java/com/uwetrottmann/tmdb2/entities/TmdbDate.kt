package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.TmdbConstants
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TmdbDate(val date: Date?) {

    constructor(date: String) : this(
                try { TMDB_DATE_FORMAT.get().parse(date) }
                catch (e: ParseException) { null })

    override fun toString(): String {
        return TMDB_DATE_FORMAT.get().format(date)
    }

    companion object {
        private val TMDB_DATE_FORMAT = object : ThreadLocal<DateFormat>() {
            public override fun initialValue(): DateFormat {
                return SimpleDateFormat(TmdbConstants.TMDB_DATE_PATTERN)
            }
        }
    }
}
