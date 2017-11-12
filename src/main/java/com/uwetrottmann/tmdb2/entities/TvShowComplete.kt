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
 * 
 */

package com.uwetrottmann.tmdb2.entities

import java.util.*

class TvShowComplete(
        id: Int,
        original_name: String? = null,
        name: String? = null,
        origin_country: List<String>? = null,
        first_air_date: Date? = null,
        backdrop_path: String? = null,
        poster_path: String? = null,
        popularity: Double? = null,
        vote_average: Double? = null,
        vote_count: Int? = null,
        original_language: String? = null,
        overview: String? = null,

        val created_by: List<Person>? = null,
        val networks: List<Network>? = null,
        val episode_run_time: List<Int>? = null,
        val genres: List<Genre>? = null,
        val homepage: String? = null,
        val in_production: Boolean = false,
        val languages: List<String>? = null,
        val last_air_date: Date? = null,
        val number_of_episodes: Int? = null,
        val number_of_seasons: Int? = null,
        val production_companies: List<ProductionCompany>? = null,
        val seasons: List<TvSeason>? = null,
        val status: String? = null,
        val type: String? = null,

        // Following are used with append_to_response
        val alternative_titles: TvAlternativeTitles? = null,
        val credits: Credits? = null,
        val release_dates: ReleaseDatesResults? = null,
        val similar: TvResultsPage? = null,
        val recommendations: TvResultsPage? = null,
        val images: Images? = null,
        val videos: Videos? = null,
        val external_ids: ExternalIds? = null,
        val content_ratings: TvContentRatings? = null
) : TvShow(
        id = id,
        original_name = original_name,
        name = name,
        overview = overview,
        original_language = original_language,
        origin_country = origin_country,
        first_air_date = first_air_date,
        backdrop_path = backdrop_path,
        poster_path = poster_path,
        popularity = popularity,
        vote_average = vote_average,
        vote_count = vote_count
)
