package com.uwetrottmann.tmdb2.enumerations

import java.util.*

enum class Status constructor(val value: String) {

    RUMORED("Rumored"),
    PLANNED("Planned"),
    IN_PRODUCTION("In Production"),
    POST_PRODUCTION("Post Production"),
    RELEASED("Released"),
    CANCELLED("Cancelled");

    override fun toString(): String {
        return value
    }

    companion object {

        private val STRING_MAPPING = HashMap<String, Status>()

        init {
            for (via in Status.values()) {
                STRING_MAPPING.put(via.toString().toUpperCase(), via)
            }
        }

        fun fromValue(value: String): Status? {
            return STRING_MAPPING[value.toUpperCase()]
        }
    }
}
