package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    /**
     * Get the primary information about a TV series by id.

     * @param tmdbId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{id}")
    fun details(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null,
            @Query("append_to_response") appendToResponse: AppendToResponse? = null
    ): Single<TvShowComplete>

    /**
     * Get the alternative titles for a specific show ID.

     * @param tmdbId A themoviedb id.
     */
    @GET("tv/{id}/alternative_titles")
    fun alternativeTitles(
            @Path("id") tmdbId: Int
    ): Single<TvAlternativeTitles>

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.

     * @param tmdbId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/credits")
    fun credits(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<Credits>

    /**
     * Get the content ratings for a specific TV show.

     * @param tmbdId A themoviedb id
     */
    @GET("tv/{id}/content_ratings")
    fun content_ratings(
            @Path("id") tmbdId: Int
    ): Single<TvContentRatings>

    /**
     * Get the external ids that we have stored for a TV series.

     * @param tmdbId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/external_ids")
    fun externalIds(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<ExternalIds>

    /**
     * Get the images (posters and backdrops) for a TV series.

     * @param tmdbId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/images")
    fun images(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<Images>

    /**
     * Get the plot keywords for a specific TV show id.

     * @param tmdbId A themoviedb id.
     */
    @GET("tv/{id}/keywords")
    fun keywords(
            @Path("id") tmdbId: Int
    ): Single<TvKeywords>

    /**
     * Get the similar TV shows for a specific tv id.

     * @param tmdbId A themoviedb id.
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/similar")
    fun similar(
            @Path("id") tmdbId: Int,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null
    ): Single<TvResultsPage>

    /**
     * Get the videos that have been added to a TV series (trailers, opening credits, etc...)

     * @param tmdbId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/{id}/videos")
    fun videos(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<Videos>

    /**
     * Get the latest TV show id.
     */
    @GET("tv/latest")
    fun latest(): Single<TvShowComplete>

    /**
     * Get the list of TV shows that are currently on the air. This query looks for any TV show that has an episode with
     * an air date in the next 7 days.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/on_the_air")
    fun onTheAir(
            @Query("page") page: Int?,
            @Query("language") language: String? = null
    ): Single<TvResultsPage>

    /**
     * Get the list of TV shows that air today. Without a specified timezone, this query defaults to EST (Eastern Time
     * UTC-05:00).

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/airing_today")
    fun airingToday(
            @Query("page") page: Int?,
            @Query("language") language: String? = null
    ): Single<TvResultsPage>

    /**
     * Get the list of top rated TV shows. By default, this list will only include TV shows that have 2 or more votes.
     * This list refreshes every day.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/top_rated")
    fun topRated(
            @Query("page") page: Int?,
            @Query("language") language: String? = null
    ): Single<TvResultsPage>

    /**
     * Get the list of popular TV shows. This list refreshes every day.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("tv/popular")
    fun popular(
            @Query("page") page: Int?,
            @Query("language") language: String? = null
    ): Single<TvResultsPage>

}
