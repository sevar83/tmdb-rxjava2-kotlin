/*
 * Copyright 2014 Chris Banes
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
 * 
 */

package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    /**
     * Get the general person information for a specific id.

     * @param tmdbId TMDb id.
     */
    @GET("person/{id}")
    fun summary(
            @Path("id") tmdbId: Int
    ): Single<Person>

    /**
     * Get the movie credits for a specific person id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("person/{id}/movie_credits")
    fun movieCredits(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<PersonCredits>

    /**
     * Get the TV credits for a specific person id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("person/{id}/tv_credits")
    fun tvCredits(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<PersonCredits>

    /**
     * Get the movie and TV credits for a specific person id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("person/{id}/combined_credits")
    fun combinedCredits(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<PersonCredits>

    /**
     * Get the external ids for a specific person id.

     * @param tmdbId TMDb id.
     */
    @GET("person/{id}/external_ids")
    fun externalIds(
            @Path("id") tmdbId: Int
    ): Single<PersonIds>

    /**
     * Get the images for a specific person id.
     */
    @GET("person/{id}/images")
    fun images(
            @Path("id") tmdbId: Int
    ): Single<PersonImages>

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a [ ] object mapped for each image.

     * @param tmdbId TMDb id.
     * *
     * @param page *Optional.* Minimum value is 1, maximum 1000, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("person/{id}/tagged_images")
    fun taggedImages(
            @Path("id") tmdbId: Int,
            @Query("page") page: Int? = null,
            @Query("language") language: String? = null
    ): Single<TaggedImagesResultsPage>

    /**
     * Get the list of popular people on The Movie Database. This list refreshes every day.
     */
    @GET("person/popular")
    fun popular(
            @Query("page") page: Int? = null
    ): Single<PersonResultsPage>

    /**
     * Get the latest person id.
     */
    @GET("person/latest")
    fun latest(): Single<Person>

}
