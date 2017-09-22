package com.uwetrottmann.tmdb2.entities

import java.util.*

open class TvShow(
        val id: Int,
        val name: String? = null,
        val overview: String? = null,
        val original_name: String? = null,
        val original_language: String? = null,
        val origin_country: List<String>? = null,
        val first_air_date: Date? = null,
        val backdrop_path: String? = null,
        val poster_path: String? = null,
        val popularity: Double? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null,
        val genre_ids: List<Int>? = null
)
