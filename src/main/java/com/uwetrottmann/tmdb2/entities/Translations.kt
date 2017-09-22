package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class Translations(
        val id: Int,
        val translations: List<Translation>? = null
) {
    class Translation {
        val iso_639_1: String? = null
        val name: String? = null
        val english_name: String? = null
    }
}
