package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.BaseResultsPage
import com.uwetrottmann.tmdb2.entities.DiscoverFilter
import com.uwetrottmann.tmdb2.entities.TmdbDate
import com.uwetrottmann.tmdb2.enumerations.ReleaseType
import com.uwetrottmann.tmdb2.enumerations.SortBy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class DiscoverServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_discover_movie() {
        val call = manager.discoverMovie()
                .page(1)
                .primary_release_date_gte(TmdbDate("1990-01-01"))
                .sort_by(SortBy.RELEASE_DATE_DESC)
                .with_cast(DiscoverFilter(TestData.PERSON_ID_BRAD_PITT))
                .with_crew(DiscoverFilter(TestData.PERSON_ID_DAVID_FINCHER))
                .without_genres(DiscoverFilter(TestData.GENRE_ID_ROMANCE))
                .with_release_type(DiscoverFilter(DiscoverFilter.Separator.OR,
                        ReleaseType.THEATRICAL, ReleaseType.DIGITAL))
                .build()
        val results = call.blockingGet()
        assertResultsPage(results)
        assertThat(results.results).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_discover_tv() {
        val call = manager.discoverTv()
                .sort_by(SortBy.VOTE_AVERAGE_DESC)
                .with_genres(DiscoverFilter(TestData.GENRE_ID_DRAMA, TestData.GENRE_ID_SCIFI))
                .with_networks(DiscoverFilter(TestData.NETWORK_ID_HBO))
                .first_air_date_gte(TmdbDate("2010-01-01"))
                .first_air_date_lte(TmdbDate("2017-01-01"))
                .build()
        val results = call.blockingGet()
        assertResultsPage(results)
        assertThat(results.results).isNotEmpty()
    }

    private fun assertResultsPage(results: BaseResultsPage) {
        assertThat(results.page).isPositive()
        assertThat(results.total_pages).isPositive()
        assertThat(results.total_results).isPositive()
    }

}
