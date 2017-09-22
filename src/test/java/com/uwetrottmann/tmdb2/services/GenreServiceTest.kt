package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.entities.GenreResults
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class GenreServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_movie() {
        val call = manager.genreService().movie(null)
        val results = call.blockingGet()
        assertGenres(results)
    }

    @Test
    @Throws(IOException::class)
    fun test_tv() {
        val call = manager.genreService().tv(null)
        val results = call.blockingGet()
        assertGenres(results)
    }

    private fun assertGenres(results: GenreResults) {
        assertThat(results).isNotNull()
        assertThat(results.genres).isNotEmpty()
        for (genre in results.genres!!) {
            assertThat(genre.id).isGreaterThan(0)
            assertThat(genre.name).isNotEmpty()
        }
    }

}
