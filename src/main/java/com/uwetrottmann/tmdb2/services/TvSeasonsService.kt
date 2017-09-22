package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.

     * @param showId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{id}/season/{season_number}")
    fun season(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int? = null,
            @Query("language") language: String? = null,
            @Query("append_to_response") appendToResponse: AppendToResponse? = null
    ): Single<TvSeason>

    /**
     * Get the cast and crew credits for a TV season by season number.

     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/credits")
    fun credits(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int? = null
    ): Single<Credits>

    /**
     * Get the external ids that we have stored for a TV season by season number.

     * @param showId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/external_ids")
    fun externalIds(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int? = null,
            @Query("language") language: String? = null
    ): Single<ExternalIds>

    /**
     * Get the images (posters) that we have stored for a TV season by season number.

     * @param showId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/images")
    fun images(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int? = null,
            @Query("language") language: String? = null
    ): Single<Images>

    /**
     * Get the videos that have been added to a TV season (trailers, teasers, etc...)

     * @param showId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/videos")
    fun videos(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int? = null,
            @Query("language") language: String? = null
    ): Single<Videos>

}
