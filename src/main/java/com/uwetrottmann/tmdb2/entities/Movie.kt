package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.enumerations.Status
import java.util.*

class Movie(

        val id: Int,

        val adult: Boolean? = null,
        val backdrop_path: String? = null,
        val belongs_to_collection: Collection? = null,
        val budget: Int? = null,
        val genres: List<Genre>? = null,
        val homepage: String? = null,
        val imdb_id: String? = null,
        val original_title: String? = null,
        val overview: String? = null,
        val popularity: Double? = null,
        val poster_path: String? = null,
        val production_companies: List<ProductionCompany>? = null,
        val production_countries: List<ProductionCountry>? = null,
        val release_date: Date? = null,
        val revenue: Long? = null,
        val runtime: Int? = null,
        val spoken_languages: List<SpokenLanguage>? = null,
        val status: Status? = null,
        val tagline: String? = null,
        val title: String? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null,

        // Following are used with append_to_response
        val alternative_titles: MovieAlternativeTitles? = null,
        val credits: Credits? = null,
        val release_dates: ReleaseDatesResults? = null,
        val similar: MovieResultsPage? = null,
        val images: Images? = null,
        val videos: Videos? = null,
        val external_ids: ExternalIds? = null
        // TODO val content_ratings
)
