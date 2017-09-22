package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.Review
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewsService {

    @GET("review/{review_id}")
    fun getDetails(
            @Path("review_id") externalId: String? = null
    ): Single<Review>
}
