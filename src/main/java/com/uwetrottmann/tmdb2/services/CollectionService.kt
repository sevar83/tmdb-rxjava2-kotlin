/*
 * Copyright 2015 Manuel Laggner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.AppendToResponse
import com.uwetrottmann.tmdb2.entities.Collection
import com.uwetrottmann.tmdb2.entities.Images
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {
    /**
     * Get the basic collection information for a specific collection id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("collection/{id}")
    fun summary(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null,
            @Query("append_to_response") appendToResponse: AppendToResponse? = null
    ): Single<Collection>

    /**
     * Get the images (posters and backdrops) for a specific collection id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("collection/{id}/images")
    fun images(
            @Path("id") tmdbId: Int,
            @Query("language") language: String? = null
    ): Single<Images>
}
