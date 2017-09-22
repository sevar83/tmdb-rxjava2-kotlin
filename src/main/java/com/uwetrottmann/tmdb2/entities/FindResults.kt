package com.uwetrottmann.tmdb2.entities

import kotlin.collections.List

class FindResults(
        val movie_results: List<Movie>? = null,
        val person_results: List<Person>? = null,
        val tv_results: List<TvShow>? = null,
        val tv_season_results: List<TvSeason>? = null,
        val tv_episode_results: List<TvEpisode>? = null
)
