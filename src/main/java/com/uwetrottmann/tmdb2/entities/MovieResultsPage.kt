package com.uwetrottmann.tmdb2.entities

class MovieResultsPage(
        page: Int,
        total_pages: Int,
        total_results: Int,
        val results: List<Movie?>? = null
) : BaseResultsPage(page, total_pages, total_results)
