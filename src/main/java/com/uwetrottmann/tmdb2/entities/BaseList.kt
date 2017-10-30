package com.uwetrottmann.tmdb2.entities

open class BaseList(
    val id: Int,
    val description: String? = null,
    val favorite_count: Int? = null,
    val item_count: Int? = null,
    val iso_639_1: String? = null,
    val name: String? = null,
    val poster_path: String? = null,
    val list_type: String? = null
)