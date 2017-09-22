package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.FindResults
import com.uwetrottmann.tmdb2.enumerations.ExternalSource
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FindService {

    /**
     * The find method makes it easy to search for objects in our database by an external id. For instance, an IMDB ID.
     * This will search all objects (movies, TV shows and people) and return the results in a single response. TV season
     * and TV episode searches will be supported shortly.

     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("find/{id}")
    fun find(
            @Path("id") externalId: String,
            @Query("external_source") source: ExternalSource? = null,
            @Query("language") language: String? = null
    ): Single<FindResults>

}
