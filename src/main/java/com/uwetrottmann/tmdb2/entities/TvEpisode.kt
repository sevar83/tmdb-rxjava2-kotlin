package com.uwetrottmann.tmdb2.entities

import org.threeten.bp.LocalDate

class TvEpisode(

        val id: Int,

        val air_date: LocalDate? = null,
        val crew: List<CrewMember>? = null,
        val episode_number: Int? = null,
        val guest_stars: List<CastMember>? = null,
        val name: String? = null,
        val overview: String? = null,
        val production_code: String? = null,
        val season_number: Int? = null,
        val still_path: String? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null,
        val images: Images? = null,
        val external_ids: ExternalIds? = null,
        val credits: Credits? = null

)
