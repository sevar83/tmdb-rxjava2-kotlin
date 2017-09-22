package com.uwetrottmann.tmdb2

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.uwetrottmann.tmdb2.enumerations.Status
import java.text.SimpleDateFormat
import java.util.*

object TmdbHelper {

    private val TMDB_DATE_FORMAT = SimpleDateFormat(TmdbConstants.TMDB_DATE_PATTERN);

    /**
     * Create a [com.google.gson.GsonBuilder] and register all of the custom types needed in order to properly
     * deserialize complex TMDb-specific types.

     * @return Assembled GSON builder instance.
     */
    // class types
    // return null instead of failing (like default parser would)
    val gsonBuilder: GsonBuilder
        get() {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(Int::class.java, JsonDeserializer<Int> { json, _, _ -> json.asInt })

            builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
                try {
                    return@JsonDeserializer TMDB_DATE_FORMAT.parse(json.asString)
                } catch (e: Exception) {
                    return@JsonDeserializer null
                }
            })

            builder.registerTypeAdapter(Status::class.java, JsonDeserializer<Status> { jsonElement, _, _ ->
                val value = jsonElement.asString
                if (value != null) {
                    Status.fromValue(value)
                } else {
                    null
                }
            })

            return builder
        }
}
