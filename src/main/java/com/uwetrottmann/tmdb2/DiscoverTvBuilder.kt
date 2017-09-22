package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.entities.DiscoverFilter
import com.uwetrottmann.tmdb2.entities.TmdbDate
import com.uwetrottmann.tmdb2.entities.TvResultsPage
import com.uwetrottmann.tmdb2.enumerations.SortBy
import com.uwetrottmann.tmdb2.services.DiscoverService
import io.reactivex.Single

class DiscoverTvBuilder(protected val discoverService: DiscoverService) {

    private var language: String? = null
    private var sort_by: SortBy? = null
    private var air_date_gte: TmdbDate? = null
    private var air_date_lte: TmdbDate? = null
    private var first_air_date_gte: TmdbDate? = null
    private var first_air_date_lte: TmdbDate? = null
    private var first_air_date_year: Int? = null
    private var page: Int? = null
    private var timezone: String? = null
    private var vote_average_gte: Float? = null
    private var vote_count_gte: Int? = null
    private var with_genres: DiscoverFilter? = null
    private var with_networks: DiscoverFilter? = null
    private var without_genres: DiscoverFilter? = null
    private var with_runtime_gte: Int? = null
    private var with_runtime_lte: Int? = null
    private var include_null_first_air_dates: Boolean? = null
    private var with_original_language: String? = null

    fun language(value: String): DiscoverTvBuilder {
        this.language = value
        return this
    }

    fun sort_by(value: SortBy): DiscoverTvBuilder {
        this.sort_by = value
        return this
    }

    fun air_date_gte(value: TmdbDate): DiscoverTvBuilder {
        this.air_date_gte = value
        return this
    }

    fun air_date_lte(value: TmdbDate): DiscoverTvBuilder {
        this.air_date_lte = value
        return this
    }

    fun first_air_date_gte(value: TmdbDate): DiscoverTvBuilder {
        this.first_air_date_gte = value
        return this
    }

    fun first_air_date_lte(value: TmdbDate): DiscoverTvBuilder {
        this.first_air_date_lte = value
        return this
    }

    fun first_air_date_year(value: Int?): DiscoverTvBuilder {
        this.first_air_date_year = value
        return this
    }

    fun page(value: Int?): DiscoverTvBuilder {
        this.page = value
        return this
    }

    fun timezone(value: String): DiscoverTvBuilder {
        this.timezone = value
        return this
    }

    fun vote_average_gte(value: Float?): DiscoverTvBuilder {
        this.vote_average_gte = value
        return this
    }

    fun vote_count_gte(value: Int?): DiscoverTvBuilder {
        this.vote_count_gte = value
        return this
    }

    fun with_genres(value: DiscoverFilter): DiscoverTvBuilder {
        this.with_genres = value
        return this
    }

    fun with_networks(value: DiscoverFilter): DiscoverTvBuilder {
        this.with_networks = value
        return this
    }

    fun without_genres(value: DiscoverFilter): DiscoverTvBuilder {
        this.without_genres = value
        return this
    }

    fun with_runtime_gte(value: Int?): DiscoverTvBuilder {
        this.with_runtime_gte = value
        return this
    }

    fun with_runtime_lte(value: Int?): DiscoverTvBuilder {
        this.with_runtime_lte = value
        return this
    }

    fun include_null_first_air_dates(): DiscoverTvBuilder {
        this.include_null_first_air_dates = true
        return this
    }

    fun with_original_language(value: String): DiscoverTvBuilder {
        this.with_original_language = value
        return this
    }

    fun build(): Single<TvResultsPage> {
        return discoverService.discoverTv(
                language,
                sort_by,
                air_date_gte,
                air_date_lte,
                first_air_date_gte,
                first_air_date_lte,
                first_air_date_year,
                page,
                timezone,
                vote_average_gte,
                vote_count_gte,
                with_genres,
                with_networks,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                include_null_first_air_dates,
                with_original_language
        )
    }

}
