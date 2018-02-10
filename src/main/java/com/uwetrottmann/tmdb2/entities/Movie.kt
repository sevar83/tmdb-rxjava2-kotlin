package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.enumerations.Status
import org.threeten.bp.LocalDate

class Movie(

        id: Int,

        adult: Boolean? = null,
        backdrop_path: String? = null,
        genres: List<Genre>? = null,
        genre_ids: List<Int>? = null,
        original_title: String? = null,
        original_language: String? = null,
        overview: String? = null,
        popularity: Double? = null,
        poster_path: String? = null,
        release_date: LocalDate? = null,
        title: String? = null,
        vote_average: Double? = null,
        vote_count: Int? = null,
        media_type: String? = null,
        rating: Int? = null,

        val belongs_to_collection: Collection? = null,
        val budget: Int? = null,
        val homepage: String? = null,
        val imdb_id: String? = null,
        val production_companies: List<ProductionCompany>? = null,
        val production_countries: List<ProductionCountry>? = null,
        val revenue: Long? = null,
        val runtime: Int? = null,
        val spoken_languages: List<SpokenLanguage>? = null,
        val status: Status? = null,
        val tagline: String? = null,

        // Following are used with append_to_response
        val alternative_titles: MovieAlternativeTitles? = null,
        val credits: Credits? = null,
        val release_dates: ReleaseDatesResults? = null,
        val similar: MovieResultsPage? = null,
        val recommendations: MovieResultsPage? = null,
        val images: Images? = null,
        val videos: Videos? = null,
        val external_ids: ExternalIds? = null
) : BaseMovie(id, adult, backdrop_path, genres, genre_ids, original_title, original_language,
        overview, popularity, poster_path, release_date, title, vote_average, vote_count, media_type, rating)
