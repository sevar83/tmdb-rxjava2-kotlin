package com.uwetrottmann.tmdb2.entities

import java.util.*

class PersonCastCredit(

        credit_id: String? = null,
        id: Int,
        media_type: String? = null,
        
        // both
        poster_path: String? = null,
        
        // movie
        adult: Boolean? = null,
        release_date: Date? = null,
        title: String? = null,
        original_title: String? = null,
        
        // tv
        first_air_date: Date? = null,
        name: String? = null,
        original_name: String? = null,
        
        // both
        val character: String? = null,

        // tv
        val episode_count: Int? = null

) : BasePersonCredit(credit_id, id, media_type, poster_path, adult, release_date, title, original_title, first_air_date, name, original_name)
