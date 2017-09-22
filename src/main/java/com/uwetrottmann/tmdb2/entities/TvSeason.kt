package com.uwetrottmann.tmdb2.entities

import java.util.*
import kotlin.collections.List

class TvSeason(
        val id: Int,
        val air_date: Date? = null,
        val episodes: List<TvEpisode>? = null,
        val episode_count: Int? = null,
        val name: String? = null,
        val overview: String? = null,
        val poster_path: String? = null,
        val season_number: Int? = null,
        val credits: Credits? = null,
        val images: Images? = null,
        val external_ids: ExternalIds? = null
)
