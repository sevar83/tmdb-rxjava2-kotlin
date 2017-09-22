package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.entities.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.assertj.core.api.Assertions.assertThat

abstract class BaseTestCase {

    internal class TestTmdb(apiKey: String) : Tmdb(apiKey) {

        override fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
            super.setOkHttpClientDefaults(builder)
            if (DEBUG) {
                // add logging
                val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { s ->
                    // standard output is easier to read
                    println(s)
                })
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(logging)
            }
        }
    }

    protected fun getManager(): Tmdb {
        return manager
    }

    companion object {

        // Do NOT use this API key in your application, it is solely for testing tmdb-java!
        private val API_KEY = "25da90e9f8f0b3892d8bdeb6c3d6267d"

        private val DEBUG = true

        internal val manager = TestTmdb(API_KEY)

        internal fun assertCrewCredits(crew: List<CrewMember>) {
            assertThat(crew).isNotNull
            assertThat(crew).isNotEmpty

            for (member in crew) {
                assertThat(member.id).isNotNull()
                assertThat(member.credit_id).isNotNull()
                assertThat(member.name).isNotNull()
                assertThat(member.department).isNotNull()
                assertThat(member.job).isNotNull()
            }
        }

        internal fun assertCastCredits(cast: List<CastMember>) {
            assertThat(cast).isNotNull
            assertThat(cast).isNotEmpty

            for (member in cast) {
                assertThat(member.id).isNotNull()
                assertThat(member.credit_id).isNotNull()
                assertThat(member.name).isNotNull()
                assertThat(member.character).isNotNull()
                assertThat(member.order).isNotNull()
            }
        }

        internal fun assertImages(images: List<Image>) {
            assertThat(images).isNotNull
            assertThat(images).isNotEmpty

            for (image in images) {
                assertThat(image.file_path).isNotNull()
                assertThat(image.width).isNotNull()
                assertThat(image.height).isNotNull()
                assertThat(image.aspect_ratio).isGreaterThan(0f)
                assertThat(image.vote_average).isGreaterThanOrEqualTo(0f)
                assertThat(image.vote_count).isGreaterThanOrEqualTo(0)
            }
        }

        fun assertMedia(list: List<Media>) {
            for (media in list) {
                if ("movie" == media.media_type) {
                    assertThat(media.adult).isNotNull()
                    assertThat(media.release_date).isNotNull()
                    assertThat(media.original_title).isNotNull()
                    assertThat(media.title).isNotNull()
                }
                assertThat(media.backdrop_path).isNotNull()
                assertThat(media.id).isNotNull()
                assertThat(media.poster_path).isNotNull()
                assertThat(media.popularity).isNotNull().isGreaterThan(0.0)
                assertThat(media.vote_average).isNotNull().isGreaterThan(0.0)
                assertThat(media.vote_count).isNotNull().isGreaterThan(0)
                assertThat(media.media_type).isNotNull()
            }
        }

        internal fun assertVideos(videos: Videos) {
            assertThat(videos.id).isNotNull()
            for (video in videos.results!!) {
                assertThat(video).isNotNull()
                assertThat(video.id).isNotNull()
                assertThat(video.iso_639_1).isNotNull()
                assertThat(video.iso_3166_1).isNotNull()
                assertThat(video.key).isNotNull()
                assertThat(video.name).isNotNull()
                assertThat(video.site).isNotNull()
                assertThat(video.size).isNotNull()
                assertThat(video.type).isNotNull()
            }
        }
    }
}
