package com.uwetrottmann.tmdb2.services

import org.assertj.core.api.Assertions.assertThat

import java.io.IOException

import org.junit.Test

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.Review

import retrofit2.Call

class ReviewsServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_getDetails() {
        val call = manager.reviewsService().getDetails(TestData.REVIEW_ID)
        val review = call.blockingGet()
        assertThat(review.author).isEqualTo(TestData.REVIEW_AUTHOR)
        assertThat(review.iso_639_1).isEqualTo(TestData.REVIEW_ISO_639_1)
        assertThat(review.media_id).isEqualTo(TestData.REVIEW_MEDIA_ID)
        assertThat(review.media_title).isEqualTo(TestData.REVIEW_MEDIA_TITLE)
        assertThat(review.media_type).isEqualTo(TestData.REVIEW_MEDIA_TYPE)
        assertThat(review.url).isEqualTo(TestData.REVIEW_URL)
    }
}
