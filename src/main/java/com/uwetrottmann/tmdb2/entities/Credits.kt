package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class Credits(
        val id: Int,
        val cast: List<CastMember>? = null,
        val crew: List<CrewMember>? = null,
        val guest_stars: List<CastMember>? = null
)
