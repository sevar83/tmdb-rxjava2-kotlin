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

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.AppendToResponse
import com.uwetrottmann.tmdb2.entities.ExternalIds
import com.uwetrottmann.tmdb2.entities.TvEpisode
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class TvEpisodesServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_episode() {
        val call = manager.tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null, null)
        val episode = call.blockingGet()
        assertTvEpisode(episode)
    }

    @Test
    @Throws(IOException::class)
    fun test_episode_with_append_to_response() {
        val call = manager.tvEpisodesService().episode(
                TestData.TVSHOW_ID, 1, 1, null,
                AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        )
        val episode = call.blockingGet()
        assertTvEpisode(episode)

        // credits
        assertThat(episode.credits).isNotNull()
        BaseTestCase.assertCrewCredits(episode.credits!!.crew!!)
        BaseTestCase.assertCastCredits(episode.credits!!.guest_stars!!)
        BaseTestCase.assertCastCredits(episode.credits!!.cast!!)

        assertThat(episode.crew).isNotNull()
        assertThat(episode.guest_stars).isNotNull()
        BaseTestCase.assertCrewCredits(episode.crew!!)
        BaseTestCase.assertCastCredits(episode.guest_stars!!)

        // images
        assertThat(episode.images).isNotNull()
        BaseTestCase.assertImages(episode.images!!.stills!!)

        // external ids
        assertThat(episode.external_ids).isNotNull()
        assertIds(episode.external_ids!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_credits() {
        val call = manager.tvEpisodesService().credits(TestData.TVSHOW_ID, 1, 1)
        val credits = call.blockingGet()
        assertThat(credits.id).isNotNull()
        BaseTestCase.assertCrewCredits(credits.crew!!)
        BaseTestCase.assertCastCredits(credits.cast!!)
        BaseTestCase.assertCastCredits(credits.guest_stars!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_externalIds() {
        val call = manager.tvEpisodesService().externalIds(TestData.TVSHOW_ID, 1, 1)
        val ids = call.blockingGet()
        assertThat(ids.id).isNotNull()
        assertIds(ids)
    }

    @Test
    @Throws(IOException::class)
    fun test_images() {
        val call = manager.tvEpisodesService().images(TestData.TVSHOW_ID, 1, 1)
        val images = call.blockingGet()
        assertThat(images.id).isNotNull()
        BaseTestCase.assertImages(images.stills!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_videos() {
        val call = manager.tvEpisodesService().videos(TestData.TVSHOW_ID, 1, 1)
        val videos = call.blockingGet()
        BaseTestCase.assertVideos(videos)
    }

    private fun assertIds(ids: ExternalIds) {
        assertThat(ids.freebase_id).isNull()
        assertThat(ids.freebase_mid).isNotNull()
        assertThat(ids.tvdb_id).isNotNull()
        assertThat(ids.imdb_id).isNotNull()
        assertThat(ids.tvrage_id).isNotNull()
    }

    private fun assertTvEpisode(episode: TvEpisode) {
        assertThat(episode.air_date).isNotNull()
        assertThat(episode.episode_number).isPositive()
        assertThat(episode.name).isNotNull()
        assertThat(episode.overview).isNotNull()
        assertThat(episode.id).isNotNull()
        assertThat(episode.season_number).isEqualTo(1)
        assertThat(episode.still_path).isNotNull()
        assertThat(episode.vote_average).isGreaterThanOrEqualTo(0.0)
        assertThat(episode.vote_count).isGreaterThanOrEqualTo(0)
    }

}
