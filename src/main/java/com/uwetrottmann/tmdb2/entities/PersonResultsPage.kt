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

import kotlin.collections.List

class PersonResultsPage(
        page: Int,
        total_pages: Int,
        total_results: Int,
        val results: List<ResultsPage>? = null
) : BaseResultsPage(page, total_pages, total_results) {

    class ResultsPage(
        val id: Int,
        val adult: Boolean? = null,
        val name: String? = null,
        val popularity: Double? = null,
        val profile_path: String? = null,
        val known_for: List<Media>? = null
    )
}
