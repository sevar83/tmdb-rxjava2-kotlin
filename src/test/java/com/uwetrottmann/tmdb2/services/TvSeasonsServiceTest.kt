package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.AppendToResponse
import com.uwetrottmann.tmdb2.entities.TvSeason
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class TvSeasonsServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_season() {
        val call = manager.tvSeasonsService().season(TestData.TVSHOW_ID, 1, null, null)
        val season = call.blockingGet()
        assertTvSeason(season)
    }

    @Test
    @Throws(IOException::class)
    fun test_season_with_append_to_response() {
        val call = manager.tvSeasonsService().season(
                TestData.TVSHOW_ID, 1, null,
                AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        )
        val season = call.blockingGet()
        assertTvSeason(season)

        // credits
        assertThat(season.credits).isNotNull()
        BaseTestCase.assertCrewCredits(season.credits!!.crew!!)
        BaseTestCase.assertCastCredits(season.credits!!.cast!!)

        // images
        assertThat(season.images).isNotNull()
        BaseTestCase.assertImages(season.images!!.posters!!)

        // external ids
        assertThat(season.external_ids).isNotNull()
        assertThat(season.external_ids!!.freebase_id).isNotNull()
        assertThat(season.external_ids!!.freebase_mid).isNotNull()
        assertThat(season.external_ids!!.tvdb_id).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_credits() {
        val call = manager.tvSeasonsService().credits(TestData.TVSHOW_ID, 1)
        val credits = call.blockingGet()
        assertThat(credits.id).isNotNull()
        BaseTestCase.assertCrewCredits(credits.crew!!)
        BaseTestCase.assertCastCredits(credits.cast!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_externalIds() {
        val call = manager.tvSeasonsService().externalIds(TestData.TVSHOW_ID, 1, null)
        val ids = call.blockingGet()
        assertThat(ids.id).isNotNull()
        assertThat(ids.freebase_id).isNotNull()
        assertThat(ids.freebase_mid).isNotNull()
        assertThat(ids.tvdb_id).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_images() {
        val call = manager.tvSeasonsService().images(TestData.TVSHOW_ID, 1, null)
        val images = call.blockingGet()
        assertThat(images.id).isNotNull()
        BaseTestCase.assertImages(images.posters!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_videos() {
        val call = manager.tvSeasonsService().videos(TestData.TVSHOW_ID, 1, null)
        val videos = call.blockingGet()
        BaseTestCase.assertVideos(videos)
    }

    private fun assertTvSeason(season: TvSeason) {
        assertThat(season.air_date).isNotNull()
        assertThat(season.name).isEqualTo("Season 1")
        assertThat(season.overview).isNotNull()
        assertThat(season.id).isNotNull()
        assertThat(season.poster_path).isNotEmpty()
        assertThat(season.season_number).isEqualTo(1)
        assertThat(season.episodes).isNotEmpty()

        for (episode in season.episodes!!) {
            BaseTestCase.assertCrewCredits(episode.crew!!)
            BaseTestCase.assertCastCredits(episode.guest_stars!!)
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

}
