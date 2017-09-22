package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.BaseTestCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.IOException

class ConfigurationServiceTest : BaseTestCase() {

    @Test
    @Throws(IOException::class)
    fun test_configuration() {
        val observable = getManager().configurationService().configuration()
        val config = observable.blockingGet()
        assertThat(config).isNotNull()
        assertThat(config.images).isNotNull()
        assertThat(config.images.base_url).isNotEmpty()
        assertThat(config.images.secure_base_url).isNotEmpty()
        assertThat(config.images.poster_sizes).isNotEmpty()
        assertThat(config.images.backdrop_sizes).isNotEmpty()
        assertThat(config.images.profile_sizes).isNotEmpty()
        assertThat(config.images.logo_sizes).isNotEmpty()
        assertThat(config.images.still_sizes).isNotEmpty()
        assertThat(config.change_keys).isNotEmpty()
    }
}
