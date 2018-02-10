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

import org.threeten.bp.LocalDate

class Media(

        val id: Int,
        /** Only for movies.  */
        val adult: Boolean? = null,
        val backdrop_path: String? = null,
        val first_air_date: LocalDate? = null,
        val genre_ids: List<Int>? = null,
        val name: String? = null,
        val origin_country: List<String>? = null,
        val original_language: String? = null,
        val original_name: String? = null,
        /** Only for movies.  */
        val original_title: String? = null,
        val overview: String? = null,
        /** Only for movies.  */
        val release_date: LocalDate? = null,
        val poster_path: String? = null,
        val popularity: Double? = null,
        /** Only for movies.  */
        val title: String? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null,
        val media_type: String? = null
)
