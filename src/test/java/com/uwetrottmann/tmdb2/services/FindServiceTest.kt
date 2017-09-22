package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.enumerations.ExternalSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class FindServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_find_movie() {
        val call = manager.findService().find(MOVIE2_IMDB_ID, ExternalSource.IMDB_ID, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.movie_results).isNotEmpty

        val movie = results.movie_results!![0]
        assertThat(movie).isNotNull()
        assertThat(movie.adult).isFalse()
        assertThat(movie.backdrop_path).isNotNull()
        assertThat(movie.id).isNotNull()
        assertThat(movie.original_title).isEqualTo(MOVIE_TITLE)
        assertThat(movie.release_date).isEqualTo("2009-08-18")
        assertThat(movie.poster_path).isNotNull()
        assertThat(movie.popularity).isNotNull()
        assertThat(movie.title).isEqualTo(MOVIE_TITLE)
        assertThat(movie.vote_average).isGreaterThanOrEqualTo(0.0)
        assertThat(movie.vote_average).isGreaterThanOrEqualTo(0.0)
    }

    @Test
    @Throws(IOException::class)
    fun test_find_people() {
        val call = manager.findService().find(TestData.PERSON_IMDB_ID, ExternalSource.IMDB_ID, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.person_results).isNotEmpty()

        val person = results.person_results!![0]
        assertThat(person).isNotNull()
        assertThat(person.id).isNotNull()
        assertThat(person.name).isEqualTo(TestData.PERSON_NAME)
        assertThat(person.profile_path).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_find_tv_show() {
        val call = manager.findService().find(TestData.TVSHOW_IMDB_ID, ExternalSource.IMDB_ID, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.tv_results).isNotEmpty

        val show = results.tv_results!![0]
        assertThat(show).isNotNull()
        assertThat(show.id).isNotNull()
        assertThat(show.original_name).isEqualTo("Breaking Bad")
        assertThat(show.name).isEqualTo("Breaking Bad")
        assertThat(show.first_air_date).isNotNull()
        assertThat(show.backdrop_path).isNotNull()
        assertThat(show.poster_path).isNotNull()
        assertThat(show.popularity).isGreaterThanOrEqualTo(0.0)
        assertThat(show.vote_average).isGreaterThanOrEqualTo(0.0)
        assertThat(show.vote_count).isGreaterThanOrEqualTo(0)
    }

    @Test
    @Throws(IOException::class)
    fun test_find_tv_season() {
        val call = manager.findService().find(TestData.TVSHOW_SEASON1_ID.toString(),
                ExternalSource.TVDB_ID, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.tv_season_results).isNotEmpty

        val season = results.tv_season_results!![0]
        assertThat(season).isNotNull()
        assertThat(season.air_date).isNotNull()
        assertThat(season.name).isNotNull()
        assertThat(season.id).isNotNull()
        assertThat(season.poster_path).isNotEmpty()
        assertThat(season.season_number).isEqualTo(1)
    }

    @Test
    @Throws(IOException::class)
    fun test_find_tv_episode() {
        val call = manager.findService().find(TestData.EPISODE_IDMB_ID, ExternalSource.IMDB_ID, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.tv_episode_results).isNotEmpty

        val episode = results.tv_episode_results!![0]
        assertThat(episode).isNotNull()
        assertThat(episode.air_date).isNotNull()
        assertThat(episode.episode_number).isPositive()
        assertThat(episode.name).isNotNull()
        assertThat(episode.id).isNotNull()
        assertThat(episode.season_number).isEqualTo(1)
        assertThat(episode.still_path).isNotNull()
        assertThat(episode.vote_average).isGreaterThanOrEqualTo(0.0)
        assertThat(episode.vote_count).isGreaterThanOrEqualTo(0)
    }

    companion object {

        private val MOVIE2_IMDB_ID = "tt0361748"
        private val MOVIE_TITLE = "Inglourious Basterds"
    }

}
