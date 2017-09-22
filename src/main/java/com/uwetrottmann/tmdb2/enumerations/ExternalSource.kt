package com.uwetrottmann.tmdb2.enumerations

enum class ExternalSource constructor(private val value: String) {

    IMDB_ID("imdb_id"),
    FREEBASE_MID("freebase_mid"),
    FREEBASE_ID("freebase_id"),
    TVRAGE_ID("tvrage_id"),
    TVDB_ID("tvdb_id");

    override fun toString(): String {
        return value
    }
}
