package com.uwetrottmann.tmdb2.entities

class TvResultsPage(
        page: Int,
        total_pages: Int,
        total_results: Int,
        val results: List<TvShow?>? = null
) : BaseResultsPage(page, total_pages, total_results)
