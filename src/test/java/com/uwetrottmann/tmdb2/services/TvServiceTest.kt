package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.AppendToResponse
import com.uwetrottmann.tmdb2.entities.TvShowComplete
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class TvServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_tvshow() {
        val call = manager.tvService().details(TestData.TVSHOW_ID, null, null)
        val show = call.blockingGet()
        assertTvShow(show)
    }

    @Test
    @Throws(IOException::class)
    fun test_tvshow_with_append_to_response() {
        val call = manager.tvService().details(
                TestData.TVSHOW_ID, null,
                AppendToResponse(AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.CONTENT_RATINGS)
        )
        val show = call.blockingGet()
        assertTvShow(show)

        // credits
        assertThat(show.credits).isNotNull()
        BaseTestCase.assertCrewCredits(show.credits!!.crew!!)
        BaseTestCase.assertCastCredits(show.credits!!.cast!!)

        // images
        assertThat(show.images).isNotNull()
        BaseTestCase.assertImages(show.images!!.backdrops!!)
        BaseTestCase.assertImages(show.images!!.posters!!)

        // external ids
        assertThat(show.external_ids).isNotNull()
        assertThat(show.external_ids!!.freebase_id).isNotNull()
        assertThat(show.external_ids!!.freebase_mid).isNotNull()
        assertThat(show.external_ids!!.tvdb_id).isNotNull()
        assertThat(show.external_ids!!.imdb_id).isNotNull()
        assertThat(show.external_ids!!.tvrage_id).isNotNull()

        // similar
        assertThat(show.similar).isNotNull()
        assertThat(show.similar!!.results).isNotNull()
        assertThat(show.similar!!.results).isNotEmpty()

        // videos
        assertThat(show.videos).isNotNull()
        assertThat(show.videos!!.results).isNotNull()
        assertThat(show.videos!!.results).isNotEmpty()

        // alternative_titles
        assertThat(show.alternative_titles).isNotNull()
        assertThat(show.alternative_titles!!.results).isNotNull()
        assertThat(show.alternative_titles!!.results).isNotEmpty()

        // content ratings
        assertThat(show.content_ratings).isNotNull()
        assertThat(show.content_ratings!!.results).isNotEmpty()
        assertThat(show.content_ratings!!.results!!.get(0).iso_3166_1).isNotNull()
        assertThat(show.content_ratings!!.results!!.get(0).rating).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_content_ratings() {
        val call = manager.tvService().content_ratings(TestData.TVSHOW_ID)
        val ratings = call.blockingGet()
        assertThat(ratings).isNotNull()
        assertThat(ratings.id).isEqualTo(TestData.TVSHOW_ID)
        assertThat(ratings.results).isNotEmpty()
        assertThat(ratings.results!!.get(0).iso_3166_1).isNotNull()
        assertThat(ratings.results!!.get(0).rating).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_alternative_titles() {
        val call = manager.tvService().alternativeTitles(TestData.TVSHOW_ID)
        val titles = call.blockingGet()
        assertThat(titles).isNotNull()
        assertThat(titles.id).isEqualTo(TestData.TVSHOW_ID)
        assertThat(titles.results).isNotEmpty()
        assertThat(titles.results!!.get(0).iso_3166_1).isNotNull()
        assertThat(titles.results!!.get(0).title).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_credits() {
        val call = manager.tvService().credits(TestData.TVSHOW_ID, null)
        val credits = call.blockingGet()
        assertThat(credits.id).isNotNull()
        BaseTestCase.assertCrewCredits(credits.crew!!)
        BaseTestCase.assertCastCredits(credits.cast!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_externalIds() {
        val call = manager.tvService().externalIds(TestData.TVSHOW_ID, null)
        val ids = call.blockingGet()
        assertThat(ids.id).isNotNull()
        assertThat(ids.freebase_id).isNotNull()
        assertThat(ids.freebase_mid).isNotNull()
        assertThat(ids.tvdb_id).isNotNull()
        assertThat(ids.imdb_id).isNotNull()
        assertThat(ids.tvrage_id).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_images() {
        val call = manager.tvService().images(TestData.TVSHOW_ID, null)
        val images = call.blockingGet()
        assertThat(images).isNotNull()
        assertThat(images.id).isEqualTo(TestData.TVSHOW_ID)
        BaseTestCase.assertImages(images.backdrops!!)
        BaseTestCase.assertImages(images.posters!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_keywords() {
        val call = manager.tvService().keywords(TestData.TVSHOW_ID)
        val keywords = call.blockingGet()
        assertThat(keywords).isNotNull()
        assertThat(keywords.id).isEqualTo(TestData.TVSHOW_ID)
        assertThat(keywords.results!!.get(0).id).isNotNull()
        assertThat(keywords.results!!.get(0).name).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_similar() {
        val call = manager.tvService().similar(TestData.TVSHOW_ID, 1, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.page).isNotNull().isPositive()
        assertThat(results.total_pages).isNotNull().isPositive()
        assertThat(results.total_results).isNotNull().isPositive()
        assertThat(results.results).isNotEmpty()
        assertThat(results.results!!.get(0).backdrop_path).isNotNull()
        assertThat(results.results!!.get(0).id).isNotNull().isPositive()
        assertThat(results.results!!.get(0).original_name).isNotNull()
        assertThat(results.results!!.get(0).first_air_date).isNotNull()
        assertThat(results.results!!.get(0).poster_path).isNotNull()
        assertThat(results.results!!.get(0).popularity).isNotNull().isPositive()
        assertThat(results.results!!.get(0).name).isNotNull()
        assertThat(results.results!!.get(0).vote_average).isNotNull().isPositive()
        assertThat(results.results!!.get(0).vote_count).isNotNull().isPositive()
    }

    @Test
    @Throws(IOException::class)
    fun test_videos() {
        val call = manager.tvService().videos(TestData.TVSHOW_ID, null)
        val videos = call.blockingGet()
        assertThat(videos).isNotNull()
        assertThat(videos.id).isEqualTo(TestData.TVSHOW_ID)
        assertThat(videos.results!!.get(0).id).isNotNull()
        assertThat(videos.results!!.get(0).iso_639_1).isNotNull()
        assertThat(videos.results!!.get(0).key).isNotNull()
        assertThat(videos.results!!.get(0).name).isNotNull()
        assertThat(videos.results!!.get(0).site).isEqualTo("YouTube")
        assertThat(videos.results!!.get(0).size).isNotNull()
        assertThat(videos.results!!.get(0).type).isEqualTo("Trailer")
    }

    @Test
    @Throws(IOException::class)
    fun test_latest() {
        val call = manager.tvService().latest()
        val show = call.blockingGet()
        // Latest show might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(show).isNotNull()
        assertThat(show.id).isPositive()
        assertThat(show.name).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_onTheAir() {
        val call = manager.tvService().onTheAir(null, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_airingToday() {
        val call = manager.tvService().airingToday(null, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_topRated() {
        val call = manager.tvService().topRated(null, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_popular() {
        val call = manager.tvService().popular(null, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.results).isNotEmpty()
    }

    private fun assertTvShow(show: TvShowComplete) {
        assertThat(show.first_air_date).isNotNull()
        assertThat(show.homepage).isNotNull()
        assertThat(show.id).isNotNull()
        assertThat(show.in_production).isNotNull()
        assertThat(show.languages).isNotEmpty()
        assertThat(show.last_air_date).isNotNull()
        assertThat(show.name).isNotNull()
        assertThat(show.number_of_seasons).isNotNull().isPositive()
        assertThat(show.original_language).isNotNull()
        assertThat(show.original_name).isNotNull()
        assertThat(show.overview).isNotNull()
        assertThat(show.popularity).isNotNull().isGreaterThanOrEqualTo(0.0)
        assertThat(show.poster_path).isNotNull()
        assertThat(show.status).isNotNull()
        assertThat(show.type).isNotNull()
        assertThat(show.vote_average).isNotNull().isGreaterThanOrEqualTo(0.0)
        assertThat(show.vote_count).isNotNull().isGreaterThanOrEqualTo(0)

        assertThat(show.created_by).isNotEmpty()
        for (person in show.created_by!!) {
            assertThat(person.id).isNotNull()
            assertThat(person.name).isNotNull()
            assertThat(person.profile_path).isNotNull()
        }

        assertThat(show.seasons).isNotEmpty()
        for (company in show.seasons!!) {
            assertThat(company.id).isNotNull()
            assertThat(company.air_date).isNotNull()
            assertThat(company.episode_count).isNotNull()
            assertThat(company.season_number).isNotNull()
        }

    }

}
