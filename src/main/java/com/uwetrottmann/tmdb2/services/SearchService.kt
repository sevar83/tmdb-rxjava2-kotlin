package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    /**
     * Search for companies by name.
     *
     * @param query CGI escaped string
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     */
    @GET("search/company")
    fun company(
            @Query("query") query: String,
            @Query("page") page: Int? = null
    ): Single<CompanyResultsPage>

    /**
     * Search for collections by name.
     *
     * @param query CGI escaped string
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("search/collection")
    fun collection(
            @Query("query") query: String,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null
    ): Single<CollectionResultsPage>

    /**
     * Search for keywords by name.
     *
     * @param query CGI escaped string
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     */
    @GET("search/keyword")
    fun keyword(
            @Query("query") query: String,
            @Query("page") page: Int? = null
    ): Single<KeywordResultsPage>

    /**
     * Search for movies by title.
     *
     * @param query CGI escaped string
     *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     *
     * @param language *Optional.* ISO 639-1 code.
     *
     * @param includeAdult *Optional.* Toggle the inclusion of adult titles. Expected value is: true or false
     *
     * @param year *Optional.* Filter the results release dates to matches that include this value.
     *
     * @param primaryReleaseYear *Optional.* Filter the results so that only the primary release dates have this
     * value.
     *
     * @param region *Optional.* Specify a ISO 3166-1 code to filter release dates. pattern: ^[A-Z]{2}$
     */
    @GET("search/movie")
    fun movie(
            @Query("query") query: String,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null,
            @Query("include_adult") includeAdult: Boolean? = null,
            @Query("year") year: Int? = null,
            @Query("primary_release_year") primaryReleaseYear: Int? = null,
            @Query("region") region: String? = null
    ): Single<MovieResultsPage>

    /**
     * Search for people by name.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     *
     * @param language *Optional.* ISO 639-1 code.
     *
     * @param includeAdult *Optional.* Toggle the inclusion of adult titles. Expected value is: true or false
     * *
     * @param region *Optional.* Specify a ISO 3166-1 code to filter release dates. pattern: ^[A-Z]{2}$
     */
    @GET("search/person")
    fun person(
            @Query("query") query: String,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null,
            @Query("include_adult") includeAdult: Boolean? = null,
            @Query("region") region: String? = null
    ): Single<PersonResultsPage>

    /**
     * Search for TV shows by title.
     *
     * @param query CGI escaped string
     *
     * @param page Minimum 1, maximum 1000.
     *
     * @param language ISO 639-1 code.
     *
     * @param firstAirDateYear Filter the results to only match shows that have an air date with this value.
     */
    @GET("search/tv")
    fun tv(
            @Query("query") query: String,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null,
            @Query("first_air_date_year") firstAirDateYear: Int? = null
    ): Single<TvResultsPage>
}
