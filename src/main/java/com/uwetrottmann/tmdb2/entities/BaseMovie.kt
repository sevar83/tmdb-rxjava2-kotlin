package com.uwetrottmann.tmdb2.entities

import java.util.*

/**
 * Created by Svetlozar Kostadinov on 27.10.17.
 */
open class BaseMovie(

        val id: Int,

        val adult: Boolean? = null,
        val backdrop_path: String? = null,
        val genres: List<Genre>? = null,
        val genre_ids: List<Int>? = null,
        val original_title: String? = null,
        val original_language: String? = null,
        val overview: String? = null,
        val popularity: Double? = null,
        val poster_path: String? = null,
        val release_date: Date? = null,
        val title: String? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null,

        val media_type: String? = null,

        rating: Int? = null
) : BaseRatingObject(rating)

