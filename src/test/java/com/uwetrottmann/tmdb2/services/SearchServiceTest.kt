package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.BaseResultsPage
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class SearchServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_companySearch() {
        val call = manager.searchService().company("Sony Pictures", null)
        val companyResults = call.blockingGet()

        assertResultsPage(companyResults)
        assertThat(companyResults.results).isNotEmpty()
        assertThat(companyResults.results!!.get(0).id).isNotNull()
        assertThat(companyResults.results!!.get(0)).isNotNull()
        assertThat(companyResults.results!!.get(0).logo_path).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_collectionSearch() {
        val call = manager.searchService().collection("The Avengers Collection", null, null)
        val collectionResults = call.blockingGet()

        assertResultsPage(collectionResults)
        assertThat(collectionResults.results).isNotEmpty()
        assertThat(collectionResults.results!!.get(0).id).isNotNull()
        assertThat(collectionResults.results!!.get(0).backdrop_path).isNotNull()
        assertThat(collectionResults.results!!.get(0).name).isNotNull()
        assertThat(collectionResults.results!!.get(0).poster_path).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_keywordSearch() {
        val call = manager.searchService().keyword("fight", null)
        val keywordResults = call.blockingGet()

        assertResultsPage(keywordResults)
        assertThat(keywordResults.results).isNotEmpty()
        assertThat(keywordResults.results!!.get(0).id).isNotNull()
        assertThat(keywordResults.results!!.get(0).name).isNotNull()
    }

    @Test
    @Throws(IOException::class)
    fun test_movieSearch() {
        val call = manager.searchService().movie(TestData.MOVIE_TITLE, null, null, null, null, null, null)
        val movieResults = call.blockingGet()

        assertResultsPage(movieResults)
        assertThat(movieResults.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_personSearch() {
        val call = manager.searchService().person(TestData.PERSON_NAME, null, null, null)
        val movieResults = call.blockingGet()

        assertResultsPage(movieResults)
        assertThat(movieResults.results!!.get(0).id).isNotNull()
        assertThat(movieResults.results!!.get(0).name).isNotNull()
        assertThat(movieResults.results!!.get(0).popularity).isNotNull()
        assertThat(movieResults.results!!.get(0).profile_path).isNotNull()
        assertThat(movieResults.results!!.get(0).adult).isNotNull()
        BaseTestCase.assertMedia(movieResults.results!!.get(0).known_for!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_tv() {
        val call = manager.searchService().tv(TestData.TVSHOW_TITLE, null, null, null)
        val tvResults = call.blockingGet()

        assertResultsPage(tvResults)
        assertThat(tvResults.results).isNotEmpty()
        assertThat(tvResults.results!!.get(0).name).isEqualTo(TestData.TVSHOW_TITLE)
    }

    private fun assertResultsPage(results: BaseResultsPage) {
        assertThat(results.page).isPositive()
        assertThat(results.total_pages).isPositive()
        assertThat(results.total_results).isPositive()
    }

}
