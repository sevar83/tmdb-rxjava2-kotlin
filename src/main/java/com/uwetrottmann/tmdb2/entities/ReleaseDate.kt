package com.uwetrottmann.tmdb2.entities

import org.threeten.bp.ZonedDateTime

class ReleaseDate(
    val certification: String? = null,
    val iso_639_1: String? = null,
    val note: String? = null,
    val release_date: ZonedDateTime? = null,
    val type: Int = 0
) {
    companion object {
        val TYPE_PREMIERE = 1
        val TYPE_THEATRICAL_LIMITED = 2
        val TYPE_THEATRICAL = 3
        val TYPE_DIGITAL = 4
        val TYPE_PHYSICAL = 5
        val TYPE_TV = 6
    }
}
