package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class Configuration(
    val images: ImagesConfiguration,
    val change_keys: List<String>? = null
) {
    class ImagesConfiguration(
            val base_url: String? = null,
            val secure_base_url: String? = null,
            val poster_sizes: List<String>? = null,
            val backdrop_sizes: List<String>? = null,
            val profile_sizes: List<String>? = null,
            val logo_sizes: List<String>? = null,
            val still_sizes: List<String>? = null
    )
}
