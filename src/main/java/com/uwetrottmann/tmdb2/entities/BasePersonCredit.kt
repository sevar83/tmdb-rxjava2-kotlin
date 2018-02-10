package com.uwetrottmann.tmdb2.entities

import org.threeten.bp.LocalDate

abstract class BasePersonCredit(

    val credit_id: String? = null,
    val id: Int,
    val media_type: String? = null,

    // both
    val poster_path: String? = null,
    val popularity: Float? = null,
    val vote_average: Float? = null,

    // movies
    val adult: Boolean? = null,
    val release_date: LocalDate? = null,
    val title: String? = null,
    val original_title: String? = null,

    // tv
    val first_air_date: LocalDate? = null,
    val name: String? = null,
    val original_name: String? = null
)
