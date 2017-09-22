package com.uwetrottmann.tmdb2.entities

import java.util.Date

abstract class BasePersonCredit(

    val credit_id: String? = null,
    val id: Int,
    val media_type: String? = null,

    // both
    val poster_path: String? = null,

    // movies
    val adult: Boolean? = null,
    val release_date: Date? = null,
    val title: String? = null,
    val original_title: String? = null,

    // tv
    val first_air_date: Date? = null,
    val name: String? = null,
    val original_name: String? = null
)
