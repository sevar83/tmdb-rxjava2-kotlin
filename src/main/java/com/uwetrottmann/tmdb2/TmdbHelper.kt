package com.uwetrottmann.tmdb2

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.TypeAdapter
import com.uwetrottmann.tmdb2.enumerations.Status
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object TmdbHelper {

    /**
     * Create a [com.google.gson.GsonBuilder] and register all of the custom types needed in order to properly
     * deserialize complex TMDb-specific types.
     *
     * @return Assembled GSON builder instance.
     */
    // class types
    // return null instead of failing (like default parser would)
    val gsonBuilder: GsonBuilder
        get() {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(Int::class.java, JsonDeserializer<Int> { json, _, _ -> json.asInt })

            val zonedDateTimeAdapter: TypeAdapter<ZonedDateTime> = ThreeTenTypeAdapters.create(ZonedDateTime::class.java,
                    DateTimeFormatter.ISO_ZONED_DATE_TIME)
            builder.registerTypeAdapter(ZonedDateTime::class.java, zonedDateTimeAdapter)

            val localDateAdapter = ThreeTenTypeAdapters.create(LocalDate::class.java, TmdbConstants.TMDB_DATE_FORMATTER)
            builder.registerTypeAdapter(LocalDate::class.java, localDateAdapter)

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
