package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class TaggedImagesResultsPage {
    inner class TaggedImage : Image() {
        val id: String? = null
        val image_type: String? = null
        val media_type: String? = null
        val media: Media? = null
    }

    val id: Int = 0
    val page: Int = 0
    val results: List<TaggedImage>? = null
}
