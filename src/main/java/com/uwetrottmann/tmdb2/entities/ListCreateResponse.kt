package com.uwetrottmann.tmdb2.entities

class ListCreateResponse(
    status_code: Int? = null,
    status_message: String? = null,

    var success: Boolean? = null,
    var list_id: Int? = null
) : Status(status_code, status_message)