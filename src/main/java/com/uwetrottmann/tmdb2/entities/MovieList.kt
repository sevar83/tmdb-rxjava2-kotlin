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

class MovieList(
        id: Int,
        description: String? = null,
        favorite_count: Int? = null,
        item_count: Int? = null,
        iso_639_1: String? = null,
        name: String? = null,
        poster_path: String? = null,
        list_type: String? = null,

        val created_by: String? = null,
        val items: List<Movie> = emptyList()
) : BaseList(id, description, favorite_count, item_count, iso_639_1, name, poster_path, list_type)
