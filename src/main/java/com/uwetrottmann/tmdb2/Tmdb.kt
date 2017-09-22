package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.interceptors.TmdbApiKeyInterceptor
import com.uwetrottmann.tmdb2.services.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 *
 *
 * Create an instance of this class and then call any of the service methods.
 *
 * The service methods take care of constructing the required [Retrofit] instance and creating the service. It
 * is recommended you use your existing OkHttp client instance by overriding [.okHttpClient].
 *
 * Only one [Retrofit] instance is created upon the first and re-used for any consequent service method call.
 */
open class Tmdb
/**
 * Create a new manager instance.

 * @param apiKey Your TMDB API key.
 */
(private val apiKey: String) {

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Gson converter and sets [.okHttpClient]
     * as its client.

     * @see .okHttpClient
     */
    protected fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.gsonBuilder.create()))
                .client(okHttpClient())
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.

     * @see .setOkHttpClientDefaults
     */
    @Synchronized protected fun okHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            setOkHttpClientDefaults(builder)
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected open fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
        builder.addInterceptor(TmdbApiKeyInterceptor(apiKey))
    }

    /**
     * Return the current [Retrofit] instance. If none exists (first call, auth changed), builds a new one.
     *
     * When building, sets the base url and a custom client with an [Interceptor] which supplies authentication
     * data.
     */
    protected fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build()
        }
        return retrofit!!
    }

    fun configurationService(): ConfigurationService {
        return getRetrofit().create(ConfigurationService::class.java)
    }

    fun findService(): FindService {
        return getRetrofit().create(FindService::class.java)
    }

    fun moviesService(): MoviesService {
        return getRetrofit().create(MoviesService::class.java)
    }

    fun personService(): PeopleService {
        return getRetrofit().create(PeopleService::class.java)
    }

    fun searchService(): SearchService {
        return getRetrofit().create(SearchService::class.java)
    }

    fun tvService(): TvService {
        return getRetrofit().create(TvService::class.java)
    }

    fun tvSeasonsService(): TvSeasonsService {
        return getRetrofit().create(TvSeasonsService::class.java)
    }

    fun tvEpisodesService(): TvEpisodesService {
        return getRetrofit().create(TvEpisodesService::class.java)
    }

    fun discoverService(): DiscoverService {
        return getRetrofit().create(DiscoverService::class.java)
    }

    fun collectionService(): CollectionService {
        return getRetrofit().create(CollectionService::class.java)
    }

    fun genreService(): GenreService {
        return getRetrofit().create(GenreService::class.java)
    }

    fun reviewsService(): ReviewsService {
        return getRetrofit().create(ReviewsService::class.java)
    }

    fun discoverMovie(): DiscoverMovieBuilder {
        return DiscoverMovieBuilder(discoverService())
    }

    fun discoverTv(): DiscoverTvBuilder {
        return DiscoverTvBuilder(discoverService())
    }

    companion object {

        val API_HOST = "api.themoviedb.org"
        val API_VERSION = "3"
        val API_URL = "https://$API_HOST/$API_VERSION/"

        /**
         * API key query parameter name.
         */
        val PARAM_API_KEY = "api_key"
    }
}
