package com.uwetrottmann.tmdb2.entities

class CastMember(
        id: Int,
        val character: String? = null,
        val order: Int? = null
) : BaseMember(id)
