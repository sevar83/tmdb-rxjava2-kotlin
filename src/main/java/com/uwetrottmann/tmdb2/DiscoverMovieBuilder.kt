package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.entities.DiscoverFilter
import com.uwetrottmann.tmdb2.entities.MovieResultsPage
import com.uwetrottmann.tmdb2.entities.TmdbDate
import com.uwetrottmann.tmdb2.enumerations.SortBy
import com.uwetrottmann.tmdb2.services.DiscoverService

import io.reactivex.Single

class DiscoverMovieBuilder(private val discoverService: DiscoverService) {

    private var language: String? = null
    private var region: String? = null
    private var sort_by: SortBy? = null
    private var certification_country: String? = null
    private var certification: String? = null
    private var certification_lte: String? = null
    private var include_adult: Boolean? = null
    private var include_video: Boolean? = null
    private var page: Int? = null
    private var primary_release_year: Int? = null
    private var primary_release_date_gte: TmdbDate? = null
    private var primary_release_date_lte: TmdbDate? = null
    private var release_date_gte: TmdbDate? = null
    private var release_date_lte: TmdbDate? = null
    private var vote_count_gte: Int? = null
    private var vote_count_lte: Int? = null
    private var vote_average_gte: Float? = null
    private var vote_average_lte: Float? = null
    private var with_cast: DiscoverFilter? = null
    private var with_crew: DiscoverFilter? = null
    private var with_companies: DiscoverFilter? = null
    private var with_genres: DiscoverFilter? = null
    private var with_keywords: DiscoverFilter? = null
    private var with_people: DiscoverFilter? = null
    private var year: Int? = null
    private var without_genres: DiscoverFilter? = null
    private var with_runtime_gte: Int? = null
    private var with_runtime_lte: Int? = null
    private var with_release_type: DiscoverFilter? = null
    private var with_original_language: String? = null

    fun language(value: String): DiscoverMovieBuilder {
        this.language = value
        return this
    }

    fun region(value: String): DiscoverMovieBuilder {
        this.region = value
        return this
    }

    fun sort_by(value: SortBy): DiscoverMovieBuilder {
        this.sort_by = value
        return this
    }

    fun certification_country(value: String): DiscoverMovieBuilder {
        this.certification_country = value
        return this
    }

    fun certification(value: String): DiscoverMovieBuilder {
        this.certification = value
        return this
    }

    fun certification_lte(value: String): DiscoverMovieBuilder {
        this.certification_lte = value
        return this
    }

    fun includeAdult(): DiscoverMovieBuilder {
        this.include_adult = true
        return this
    }

    fun includeVideo(): DiscoverMovieBuilder {
        this.include_video = true
        return this
    }

    fun page(value: Int?): DiscoverMovieBuilder {
        this.page = value
        return this
    }

    fun primary_release_year(value: Int?): DiscoverMovieBuilder {
        this.primary_release_year = value
        return this
    }

    fun primary_release_date_gte(value: TmdbDate): DiscoverMovieBuilder {
        this.primary_release_date_gte = value
        return this
    }

    fun primary_release_date_lte(value: TmdbDate): DiscoverMovieBuilder {
        this.primary_release_date_lte = value
        return this
    }

    fun release_date_gte(value: TmdbDate): DiscoverMovieBuilder {
        this.release_date_gte = value
        return this
    }

    fun release_date_lte(value: TmdbDate): DiscoverMovieBuilder {
        this.release_date_lte = value
        return this
    }

    fun vote_count_gte(value: Int?): DiscoverMovieBuilder {
        this.vote_count_gte = value
        return this
    }

    fun vote_count_lte(value: Int?): DiscoverMovieBuilder {
        this.vote_count_lte = value
        return this
    }

    fun vote_average_gte(value: Float?): DiscoverMovieBuilder {
        this.vote_average_gte = value
        return this
    }

    fun vote_average_lte(value: Float?): DiscoverMovieBuilder {
        this.vote_average_lte = value
        return this
    }

    fun with_cast(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_cast = value
        return this
    }

    fun with_crew(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_crew = value
        return this
    }

    fun with_companies(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_companies = value
        return this
    }

    fun with_genres(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_genres = value
        return this
    }

    fun with_keywords(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_keywords = value
        return this
    }

    fun with_people(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_people = value
        return this
    }

    fun year(value: Int?): DiscoverMovieBuilder {
        this.year = value
        return this
    }

    fun without_genres(value: DiscoverFilter): DiscoverMovieBuilder {
        this.without_genres = value
        return this
    }

    fun with_runtime_gte(value: Int?): DiscoverMovieBuilder {
        this.with_runtime_gte = value
        return this
    }

    fun with_runtime_lte(value: Int?): DiscoverMovieBuilder {
        this.with_runtime_lte = value
        return this
    }

    fun with_release_type(value: DiscoverFilter): DiscoverMovieBuilder {
        this.with_release_type = value
        return this
    }

    fun with_original_language(value: String): DiscoverMovieBuilder {
        this.with_original_language = value
        return this
    }

    fun build(): Single<MovieResultsPage> {
        return discoverService.discoverMovie(
                language,
                region,
                sort_by,
                certification_country,
                certification,
                certification_lte,
                include_adult,
                include_video,
                page,
                primary_release_year,
                primary_release_date_gte,
                primary_release_date_lte,
                release_date_gte,
                release_date_lte,
                vote_count_gte,
                vote_count_lte,
                vote_average_gte,
                vote_average_lte,
                with_cast,
                with_crew,
                with_companies,
                with_genres,
                with_keywords,
                with_people,
                year,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                with_release_type,
                with_original_language
        )
    }

}
