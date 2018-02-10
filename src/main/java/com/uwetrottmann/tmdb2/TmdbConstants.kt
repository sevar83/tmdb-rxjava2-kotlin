package com.uwetrottmann.tmdb2

import org.threeten.bp.format.DateTimeFormatter

/**
 * Created by sevar on 16.02.17.
 */
object TmdbConstants {
    /**
     * The starting index for query pagination.
     */
    const val FIRST_PAGE_INDEX = 1
    const val ENTRIES_PER_PAGE = 20

    const val RESPONSE_HEADER_REQUEST_LIMIT = "X-RateLimit-Limit"
    const val RESPONSE_HEADER_REQUEST_REMAINING = "X-RateLimit-Remaining"
    const val RESPONSE_HEADER_REQUEST_REMAINING_RESET = "X-RateLimit-Reset"
    const val HTTP_CODE_REQUEST_LIMIT_EXCEEDED = 429

    const val TMDB_DATE_PATTERN = "yyyy-MM-dd"
    val TMDB_DATE_FORMATTER = DateTimeFormatter.ofPattern(TMDB_DATE_PATTERN)
}