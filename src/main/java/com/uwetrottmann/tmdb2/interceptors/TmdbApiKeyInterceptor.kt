package com.uwetrottmann.tmdb2.interceptors

import com.uwetrottmann.tmdb2.Tmdb
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * [Interceptor] to add the API key query parameter. As it modifies the URL ensure this is added as regular
 * interceptor, otherwise caching will be broken.
 */
class TmdbApiKeyInterceptor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return handleIntercept(chain, apiKey)
    }

    companion object {

        /**
         * If the host matches [Tmdb.API_HOST] adds a query parameter with the API key.
         */
        @JvmStatic
        @Throws(IOException::class)
        fun handleIntercept(chain: Interceptor.Chain, apiKey: String): Response {
            val request = chain.request()
            if (Tmdb.API_HOST != request.url().host()) {
                // do not intercept requests for other hosts
                // this allows the interceptor to be used on a shared okhttp client
                return chain.proceed(request)
            }

            // add (or replace) the API key query parameter
            val urlBuilder = request.url().newBuilder()
            urlBuilder.setEncodedQueryParameter(Tmdb.PARAM_API_KEY, apiKey)

            val builder = request.newBuilder()
            builder.url(urlBuilder.build())

            return chain.proceed(builder.build())
        }
    }

}
