package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class TvResultsPage(
        page: Int,
        total_pages: Int,
        total_results: Int,
        val results: List<TvShow>? = null
) : BaseResultsPage(page, total_pages, total_results)
