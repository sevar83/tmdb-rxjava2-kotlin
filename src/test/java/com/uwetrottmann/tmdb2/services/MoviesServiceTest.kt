package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.AppendToResponse
import com.uwetrottmann.tmdb2.entities.Movie
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResult
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.io.IOException
import java.text.ParseException

class MoviesServiceTest : BaseTestCase() {

    @Test
    @Throws(ParseException::class, IOException::class)
    fun test_summary() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null, null)
        val movie = call.blockingGet()
        assertMovie(movie)
        assertThat(movie.original_title).isEqualTo(TestData.MOVIE_TITLE)
    }

    @Test
    @Throws(ParseException::class, IOException::class)
    fun test_summary_language() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, "pt-BR", null)
        val movie = call.blockingGet()
        assertThat(movie).isNotNull()
        assertThat(movie.title).isEqualTo("Clube da Luta")
    }

    @Test
    @Throws(ParseException::class, IOException::class)
    fun test_summary_with_collection() {
        val call = manager.moviesService().details(TestData.MOVIE_WITH_COLLECTION_ID, null, null)
        val movie = call.blockingGet()
        assertThat(movie.title).isEqualTo(TestData.MOVIE_WITH_COLLECTION_TITLE)
        assertThat(movie.belongs_to_collection).isNotNull()
        assertThat(movie.belongs_to_collection!!.id).isEqualTo(1241)
        assertThat(movie.belongs_to_collection!!.name).isEqualTo("Harry Potter Collection")
    }

    private fun assertMovie(movie: Movie) {
        assertThat(movie).isNotNull()
        assertThat(movie.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(movie.title).isEqualTo(TestData.MOVIE_TITLE)
        assertThat(movie.overview).isNotEmpty()
        assertThat(movie.tagline).isNotEmpty()
        assertThat(movie.adult).isFalse()
        assertThat(movie.backdrop_path).isNotEmpty()
        assertThat(movie.budget).isEqualTo(63000000)
        assertThat(movie.imdb_id).isEqualTo(TestData.MOVIE_IMDB)
        assertThat(movie.poster_path).isNotEmpty()
        assertThat(movie.release_date).isEqualTo("1999-10-14")
        assertThat(movie.revenue).isEqualTo(100853753)
        assertThat(movie.runtime).isEqualTo(139)
        assertThat(movie.vote_average).isPositive()
        assertThat(movie.vote_count).isPositive()
        assertThat(movie.status).isEqualTo(TestData.STATUS)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_videos() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.VIDEOS))
        val movie = call.blockingGet()

        assertNotNull(movie.videos)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_alternative_titles() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.ALTERNATIVE_TITLES))
        val movie = call.blockingGet()

        assertNotNull(movie.alternative_titles)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_credits() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.CREDITS))
        val movie = call.blockingGet()

        assertNotNull(movie.credits)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_release_dates() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES))
        val movie = call.blockingGet()

        assertNotNull(movie.release_dates)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_similar() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.SIMILAR))
        val movie = call.blockingGet()

        assertNotNull(movie.similar)
    }

    @Test
    @Throws(IOException::class)
    fun test_summary_append_all() {
        val call = manager.moviesService().details(TestData.MOVIE_ID, null,
                AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR))
        val movie = call.blockingGet()

        assertNotNull(movie.release_dates)
        assertNotNull(movie.release_dates!!.results)
        assertThat(movie.release_dates!!.results).isNotEmpty()
        assertNotNull(movie.credits)
        assertNotNull(movie.videos)
        assertNotNull(movie.similar)
        assertNotNull(movie.alternative_titles)
    }

    @Test
    @Throws(IOException::class)
    fun test_alternative_titles() {
        val call = manager.moviesService().alternativeTitles(TestData.MOVIE_ID, null)
        val titles = call.blockingGet()
        assertThat(titles).isNotNull()
        assertThat(titles.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(titles.titles).isNotEmpty()
        assertThat(titles.titles!!.get(0).iso_3166_1).hasSize(2)
        assertThat(titles.titles!!.get(0).title).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_credits() {
        val call = manager.moviesService().credits(TestData.MOVIE_ID)
        val credits = call.blockingGet()
        assertThat(credits).isNotNull()
        assertThat(credits.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(credits.cast).isNotEmpty()
        assertThat(credits.cast!!.get(0)).isNotNull()
        assertThat(credits.cast!!.get(0).name).isEqualTo("Edward Norton")
        assertThat(credits.crew).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_images() {
        val call = manager.moviesService().images(TestData.MOVIE_ID, null)
        val images = call.blockingGet()
        assertThat(images).isNotNull()
        assertThat(images.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(images.backdrops).isNotEmpty()
        assertThat(images.backdrops!!.get(0).file_path).isNotEmpty()
        assertThat(images.backdrops!!.get(0).width).isGreaterThanOrEqualTo(1280)
        assertThat(images.backdrops!!.get(0).height).isGreaterThanOrEqualTo(720)
        if (images.backdrops!!.get(0).iso_639_1 != null) {
            assertThat(images.backdrops!!.get(0).iso_639_1).hasSize(2)
        }
        assertThat(images.backdrops!!.get(0).aspect_ratio).isGreaterThan(1.7f)
        assertThat(images.backdrops!!.get(0).vote_average).isPositive()
        assertThat(images.backdrops!!.get(0).vote_count).isPositive()
        assertThat(images.posters).isNotEmpty()
        assertThat(images.posters!!.get(0).file_path).isNotEmpty()
        assertThat(images.posters!!.get(0).width).isPositive()
        assertThat(images.posters!!.get(0).height).isPositive()
        assertThat(images.posters!!.get(0).iso_639_1).hasSize(2)
        assertThat(images.posters!!.get(0).aspect_ratio).isGreaterThan(0.6f)
        assertThat(images.posters!!.get(0).vote_average).isPositive()
        assertThat(images.posters!!.get(0).vote_count).isPositive()
    }

    @Test
    @Throws(IOException::class)
    fun test_keywords() {
        val call = manager.moviesService().keywords(TestData.MOVIE_ID)
        val keywords = call.blockingGet()
        assertThat(keywords).isNotNull()
        assertThat(keywords.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(keywords.keywords!!.get(0).id).isEqualTo(825)
        assertThat(keywords.keywords!!.get(0).name).isEqualTo("support group")
    }

    @Test
    @Throws(IOException::class)
    fun test_release_dates() {
        val call = manager.moviesService().releaseDates(TestData.MOVIE_ID)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.id).isEqualTo(TestData.MOVIE_ID)
        assertThat(results.results).isNotNull()
        assertThat(results.results!!.isEmpty()).isFalse()

        var usResult: ReleaseDatesResult? = null
        for (result in results.results!!) {
            assertThat(result.iso_3166_1).isNotNull()
            if (result.iso_3166_1 == "US") {
                usResult = result
            }
        }

        assertThat(usResult).isNotNull()
        assertThat(usResult!!.release_dates).isNotNull()
        assertThat(usResult.release_dates!!.isEmpty()).isFalse()
        assertThat(usResult.release_dates!![0].iso_639_1).isNotNull()
        assertThat(usResult.release_dates!![0].certification).isEqualTo("R")
        assertThat(usResult.release_dates!![0].release_date).isEqualTo("1999-10-14T00:00:00.000Z")
        assertThat(usResult.release_dates!![0].note).isNotNull()
        assertThat(usResult.release_dates!![0].type).isBetween(1, 6)
    }

    @Test
    @Throws(IOException::class)
    fun test_videos() {
        val call = manager.moviesService().videos(TestData.MOVIE_ID, null)
        val videos = call.blockingGet()
        assertThat(videos).isNotNull()
        assertThat(videos.id).isEqualTo(TestData.MOVIE_ID)
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
    fun test_translations() {
        val call = manager.moviesService().translations(TestData.MOVIE_ID, null)
        val translations = call.blockingGet()
        assertThat(translations).isNotNull()
        assertThat(translations.id).isEqualTo(TestData.MOVIE_ID)
        for (translation in translations.translations!!) {
            assertThat(translation.name).isNotNull()
            assertThat(translation.iso_639_1).isNotNull()
            assertThat(translation.english_name).isNotNull()
        }
    }

    @Test
    @Throws(IOException::class)
    fun test_similar() {
        val call = manager.moviesService().similar(TestData.MOVIE_ID, 3, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.page).isNotNull().isPositive()
        assertThat(results.total_pages).isNotNull().isPositive()
        assertThat(results.total_results).isNotNull().isPositive()
        assertThat(results.results).isNotEmpty()
        assertThat(results.results!!.get(0).adult).isEqualTo(false)
        assertThat(results.results!!.get(0).backdrop_path).isNotNull()
        assertThat(results.results!!.get(0).id).isNotNull().isPositive()
        assertThat(results.results!!.get(0).original_title).isNotNull()
        assertThat(results.results!!.get(0).release_date).isNotNull()
        assertThat(results.results!!.get(0).poster_path).isNotNull()
        assertThat(results.results!!.get(0).popularity).isNotNull().isPositive()
        assertThat(results.results!!.get(0).title).isNotNull()
        assertThat(results.results!!.get(0).vote_average).isNotNull().isPositive()
        assertThat(results.results!!.get(0).vote_count).isNotNull().isPositive()
    }

    @Test
    @Throws(IOException::class)
    fun test_reviews() {
        val call = manager.moviesService().reviews(49026, 1, null)
        val results = call.blockingGet()
        assertThat(results).isNotNull()
        assertThat(results.id).isNotNull()
        assertThat(results.page).isNotNull().isPositive()
        assertThat(results.total_pages).isNotNull().isPositive()
        assertThat(results.total_results).isNotNull().isPositive()
        assertThat(results.results).isNotEmpty()
        assertThat(results.results!!.get(0).id).isNotNull()
        assertThat(results.results!!.get(0).author).isNotNull()
        assertThat(results.results!!.get(0).content).isNotNull()
        assertThat(results.results!!.get(0).url).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_latest() {
        val call = manager.moviesService().latest()
        val movie = call.blockingGet()
        // Latest movie might not have a complete TMDb entry, but should at least some basic properties.
        assertThat(movie).isNotNull()
        assertThat(movie.id).isPositive()
        assertThat(movie.title).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_upcoming() {
        val call = manager.moviesService().upcoming(null, null)
        val page = call.blockingGet()
        assertThat(page).isNotNull()
        assertThat(page.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_nowPlaying() {
        val call = manager.moviesService().nowPlaying(null, null)
        val page = call.blockingGet()
        assertThat(page).isNotNull()
        assertThat(page.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_popular() {
        val call = manager.moviesService().popular(null, null)
        val page = call.blockingGet()
        assertThat(page).isNotNull()
        assertThat(page.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_topRated() {
        val call = manager.moviesService().topRated(null, null)
        val page = call.blockingGet()
        assertThat(page).isNotNull()
        assertThat(page.results).isNotEmpty()
    }

}
