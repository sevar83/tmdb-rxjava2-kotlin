package com.uwetrottmann.tmdb2.interceptors

import android.util.Log
import com.uwetrottmann.tmdb2.TmdbConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * The API limits your number of requests.
 * [http://docs.themoviedb.apiary.io/introduction/request-rate-limiting]
 *
 * Source:
 * [https://github.com/xavierlepretre/the-movie-db/blob/master/net/src/main/java/com/github/xavierlepretre/tmdb/net/RateLimitingInterceptor.java]
 */
class TmdbRateLimitingInterceptor(

        private val maxRetries: Long = 3,
        private val remainingRequests: AtomicInteger = AtomicInteger(Integer.MAX_VALUE),
        // Contains the date in seconds.
        private val resetDate: AtomicLong = AtomicLong(0)) : Interceptor {

    companion object {
        const val LOG_TAG = "RateLimitInterceptor"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return intercept(chain, maxRetries)
    }

    @Throws(IOException::class)
    internal fun intercept(chain: Interceptor.Chain, retriesLeft: Long): Response {

        val request = chain.request()
        if (remainingRequests.get() == 0) {
            val toWait = resetDate.get() * 1000 - Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis
            if (toWait > 0) {
                try {
                    Thread.sleep(toWait)
                } catch (e: InterruptedException) {
                    Log.e(LOG_TAG, e.toString())
                }

            }
        }
        val response = chain.proceed(request)
        try {
            remainingRequests.set(Integer.parseInt(
                    response.header(TmdbConstants.RESPONSE_HEADER_REQUEST_REMAINING)))
            // We do not care about the reset date if we could not read the remaining requests.
            resetDate.set(
                    response.header(TmdbConstants.RESPONSE_HEADER_REQUEST_REMAINING_RESET)?.toLong()
                            ?: throw IOException("Missing X-RateLimit-Reset header in response"))
        } catch (e: NumberFormatException) {
            Log.e(LOG_TAG, e.toString())
        }

        if (response.code() == TmdbConstants.HTTP_CODE_REQUEST_LIMIT_EXCEEDED && 0 < retriesLeft) {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                Log.e(LOG_TAG, e.toString())
            }

            return intercept(chain, retriesLeft - 1)
        }

        return response
    }
}