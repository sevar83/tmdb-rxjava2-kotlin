package com.uwetrottmann.tmdb2.entities

import org.threeten.bp.LocalDate

class PersonCrewCredit(

        credit_id: String? = null,
        id: Int,
        media_type: String? = null,

        // both
        poster_path: String? = null,
        popularity: Float? = null,
        vote_average: Float? = null,

        // movie
        adult: Boolean? = null,
        release_date: LocalDate? = null,
        title: String? = null,
        original_title: String? = null,

        // tv
        first_air_date: LocalDate? = null,
        name: String? = null,
        original_name: String? = null,

        // both
        val job: String? = null,
        val department: String? = null

) : BasePersonCredit(credit_id, id, media_type, poster_path, popularity, vote_average, adult, release_date, title, original_title, first_air_date, name, original_name)
