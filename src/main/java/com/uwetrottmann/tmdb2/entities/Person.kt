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

package com.uwetrottmann.tmdb2.entities

import org.threeten.bp.LocalDate

class Person(
    val id: Int,
    val name: String? = null,
    val place_of_birth: String? = null,
    val profile_path: String? = null,
    val homepage: String? = null,
    val biography: String? = null,
    val birthday: LocalDate? = null,
    val deathday: LocalDate? = null,

    // Following are used with append_to_response
    val movie_credits: PersonCredits? = null,
    val tv_credits: PersonCredits? = null,
    val combined_credits: PersonCredits? = null,
    val external_ids: ExternalIds? = null,
    val images: PersonImages? = null
    //val tagged_images: TaggedImages? = null
)
