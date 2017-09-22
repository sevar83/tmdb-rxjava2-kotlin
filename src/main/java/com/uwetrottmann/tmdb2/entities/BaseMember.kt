package com.uwetrottmann.tmdb2.entities

abstract class BaseMember(

    val id: Int,
    val credit_id: String? = null,
    val name: String? = null,
    val profile_path: String? = null
)
