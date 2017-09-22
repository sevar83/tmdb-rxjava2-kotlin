/*
 * Copyright 2015 Miguel Teixeira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.DiscoverFilter
import com.uwetrottmann.tmdb2.entities.MovieResultsPage
import com.uwetrottmann.tmdb2.entities.TmdbDate
import com.uwetrottmann.tmdb2.entities.TvResultsPage
import com.uwetrottmann.tmdb2.enumerations.SortBy
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {

    /**
     * Discover movies by different types of data like average rating, number of votes, genres and certifications.

     * @see [Movie Discover](https://developers.themoviedb.org/3/discover/movie-discover)
     */
    @GET("discover/movie")
    fun discoverMovie(
            @Query("language") language: String? = null,
            @Query("region") region: String? = null,
            @Query("sort_by") sort_by: SortBy? = null,
            @Query("certification_country") certification_country: String? = null,
            @Query("certification") certification: String? = null,
            @Query("certification_lte") certification_lte: String? = null,
            @Query("include_adult") include_adult: Boolean? = null,
            @Query("include_video") include_video: Boolean? = null,
            @Query("page") page: Int? = null,
            @Query("primary_release_year") primary_release_year: Int? = null,
            @Query("primary_release_date.gte") primary_release_date_gte: TmdbDate? = null,
            @Query("primary_release_date.lte") primary_release_date_lte: TmdbDate? = null,
            @Query("release_date.gte") release_date_gte: TmdbDate? = null,
            @Query("release_date.lte") release_date_lte: TmdbDate? = null,
            @Query("vote_count.gte") vote_count_gte: Int? = null,
            @Query("vote_count.lte") vote_count_lte: Int? = null,
            @Query("vote_average.gte") vote_average_gte: Float? = null,
            @Query("vote_average.lte") vote_average_lte: Float? = null,
            @Query("with_cast") with_cast: DiscoverFilter? = null,
            @Query("with_crew") with_crew: DiscoverFilter? = null,
            @Query("with_companies") with_companies: DiscoverFilter? = null,
            @Query("with_genres") with_genres: DiscoverFilter? = null,
            @Query("with_keywords") with_keywords: DiscoverFilter? = null,
            @Query("with_people") with_people: DiscoverFilter? = null,
            @Query("year") year: Int? = null,
            @Query("without_genres") without_genres: DiscoverFilter? = null,
            @Query("with_runtime.gte") with_runtime_gte: Int? = null,
            @Query("with_runtime.lte") with_runtime_lte: Int? = null,
            @Query("with_release_type") with_release_type: DiscoverFilter? = null,
            @Query("with_original_language") with_original_language: String? = null
    ): Single<MovieResultsPage>

    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres, the network they aired
     * on and air dates.

     * @see [TV Discover](https://developers.themoviedb.org/3/discover/tv-discover)
     */
    @GET("discover/tv")
    fun discoverTv(
            @Query("language") language: String? = null,
            @Query("sort_by") sort_by: SortBy? = null,
            @Query("air_date.gte") air_date_gte: TmdbDate? = null,
            @Query("air_date.lte") air_date_lte: TmdbDate? = null,
            @Query("first_air_date.gte") first_air_date_gte: TmdbDate? = null,
            @Query("first_air_date.lte") first_air_date_lte: TmdbDate? = null,
            @Query("first_air_date_year") first_air_date_year: Int? = null,
            @Query("page") page: Int? = null,
            @Query("timezone") timezone: String? = null,
            @Query("vote_average.gte") vote_average_gte: Float? = null,
            @Query("vote_count.gte") vote_count_gte: Int? = null,
            @Query("with_genres") with_genres: DiscoverFilter? = null,
            @Query("with_networks") with_networks: DiscoverFilter? = null,
            @Query("without_genres") without_genres: DiscoverFilter? = null,
            @Query("with_runtime.gte") with_runtime_gte: Int? = null,
            @Query("with_runtime.lte") with_runtime_lte: Int? = null,
            @Query("include_null_first_air_dates") include_null_first_air_dates: Boolean? = null,
            @Query("with_original_language") with_original_language: String? = null
    ): Single<TvResultsPage>
}
