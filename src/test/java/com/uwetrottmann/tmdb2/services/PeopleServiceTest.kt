package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import com.uwetrottmann.tmdb2.TestData
import com.uwetrottmann.tmdb2.entities.PersonCredits
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat

class PeopleServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class, ParseException::class)
    fun test_summary() {
        val call = manager.personService().summary(TestData.PERSON_ID)
        val person = call.blockingGet()
        assertThat(person).isNotNull()
        assertThat(person.id).isEqualTo(TestData.PERSON_ID)
        assertThat(TestData.PERSON_NAME).isEqualTo(TestData.PERSON_NAME)
        assertThat(person.birthday).isEqualTo(JSON_STRING_DATE.parse("1944-05-14"))
        assertThat(person.deathday).isNull()
        assertThat(person.profile_path).startsWith("/").endsWith(".jpg")
        assertThat(person.biography).isNotEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun test_movie_credits() {
        val call = manager.personService().movieCredits(TestData.PERSON_ID, null)
        val credits = call.blockingGet()
        assertThat(credits.id).isEqualTo(TestData.PERSON_ID)
        assertCastCredits(credits, false)
        assertCrewCredits(credits, false)

        for (credit in credits.cast!!) {
            assertThat(credit.title).isNotEmpty()
        }
    }

    @Test
    @Throws(IOException::class)
    fun test_tv_credits() {
        val call = manager.personService().tvCredits(TestData.PERSON_ID, null)
        val credits = call.blockingGet()
        assertThat(credits.id).isEqualTo(TestData.PERSON_ID)
        assertCastCredits(credits, false)

        for (credit in credits.cast!!) {
            assertThat(credit.episode_count).isGreaterThanOrEqualTo(0)
            assertThat(credit.name).isNotEmpty()
        }
    }

    @Test
    @Throws(IOException::class)
    fun test_combined_credits() {
        val call = manager.personService().combinedCredits(TestData.PERSON_ID, null)
        val credits = call.blockingGet()
        assertThat(credits.id).isEqualTo(TestData.PERSON_ID)
        assertCastCredits(credits, true)
        assertCrewCredits(credits, true)
    }

    @Test
    @Throws(IOException::class)
    fun test_external_ids() {
        val call = manager.personService().externalIds(TestData.PERSON_ID)
        val ids = call.blockingGet()
        assertThat(ids.id).isEqualTo(TestData.PERSON_ID)
        assertThat(ids.imdb_id).isEqualTo("nm0000184")
        assertThat(ids.freebase_id).isEqualTo("/en/george_lucas")
        assertThat(ids.freebase_mid).isEqualTo("/m/0343h")
        assertThat(ids.tvrage_id).isEqualTo(6490)
    }

    @Test
    @Throws(IOException::class)
    fun test_images() {
        val call = manager.personService().images(TestData.PERSON_ID)
        val images = call.blockingGet()
        assertThat(images.id).isEqualTo(TestData.PERSON_ID)

        for (image in images.profiles!!) {
            assertThat(image.file_path).isNotEmpty()
            assertThat(image.width).isNotNull()
            assertThat(image.height).isNotNull()
            assertThat(image.aspect_ratio).isGreaterThan(0f)
        }
    }

    @Test
    @Throws(IOException::class)
    fun test_tagged_images() {
        val call = manager.personService().taggedImages(TestData.PERSON_ID, null, null)
        val images = call.blockingGet()
        assertThat(images.id).isEqualTo(TestData.PERSON_ID)

        for (image in images.results!!) {
            assertThat(image.file_path).isNotEmpty()
            assertThat(image.width).isNotNull()
            assertThat(image.width).isGreaterThan(0)
            assertThat(image.height).isNotNull()
            assertThat(image.height).isGreaterThan(0)
            assertThat(image.aspect_ratio).isGreaterThan(0f)
            assertThat(image.media).isNotNull()
            assertThat(image.id).isNotNull()
            assertThat(image.media_type).isNotNull()
            assertThat(image.image_type).isNotNull()
        }
    }

    @Test
    @Throws(IOException::class)
    fun test_popular() {
        val call = manager.personService().popular(null)
        val popular = call.blockingGet()

        assertThat(popular.results!!.get(0).id).isNotNull()
        assertThat(popular.results!!.get(0).name).isNotNull()
        assertThat(popular.results!!.get(0).popularity).isPositive()
        assertThat(popular.results!!.get(0).profile_path).isNotEmpty()
        assertThat(popular.results!!.get(0).adult).isNotNull()
        BaseTestCase.assertMedia(popular!!.results!!.get(0).known_for!!)
    }

    @Test
    @Throws(IOException::class)
    fun test_latest() {
        val call = manager.personService().latest()
        val person = call.blockingGet()
        // Latest person might not have a complete TMDb entry, but at should least some basic properties.
        assertThat(person).isNotNull()
        assertThat(person.name).isNotEmpty()
        assertThat(person.id).isPositive()
    }

    private fun assertCastCredits(credits: PersonCredits, hasMediaType: Boolean) {
        // assert cast credits
        assertThat(credits.cast).isNotEmpty()
        for (credit in credits.cast!!) {
            assertThat(credit.character).isNotNull() // may be empty

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty()
            }
        }
    }

    private fun assertCrewCredits(credits: PersonCredits, hasMediaType: Boolean) {
        // assert crew credits
        assertThat(credits.crew).isNotEmpty()
        for (credit in credits.crew!!) {
            // may be empty, but should exist
            assertThat(credit.department).isNotNull()
            assertThat(credit.job).isNotNull()

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty()
            }
        }
    }

    companion object {

        private val JSON_STRING_DATE = SimpleDateFormat("yyy-MM-dd")
    }

}
