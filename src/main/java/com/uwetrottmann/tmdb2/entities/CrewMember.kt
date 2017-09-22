package com.uwetrottmann.tmdb2.entities

class CrewMember(
        id: Int,
        val department: String? = null,
        val job: String? = null
) : BaseMember(id)
