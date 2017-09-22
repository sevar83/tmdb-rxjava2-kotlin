package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.enumerations.Status

interface TestData {
    companion object {

        val MOVIE_ID = 550
        val MOVIE_TITLE = "Fight Club"
        val MOVIE_IMDB = "tt0137523"
        val STATUS = Status.RELEASED

        val GENRE_ID_DRAMA = 18
        val GENRE_ID_ROMANCE = 10749
        val GENRE_ID_SCIFI = 10765

        val NETWORK_ID_HBO = 49

        val PERSON_ID_BRAD_PITT = 287
        val PERSON_ID_DAVID_FINCHER = 7467

        //Test Data for ReviewsService
        val REVIEW_ID = "581bbdbbc3a36805c60001f1"
        val REVIEW_AUTHOR = "iheardthatmoviewas"
        val REVIEW_URL = "https://www.themoviedb.org/review/581bbdbbc3a36805c60001f1"
        val REVIEW_ISO_639_1 = "en"
        val REVIEW_MEDIA_ID = 284052
        val REVIEW_MEDIA_TITLE = "Doctor Strange"
        val REVIEW_MEDIA_TYPE = "Movie"

        val MOVIE_WITH_COLLECTION_ID = 671
        val MOVIE_WITH_COLLECTION_TITLE = "Harry Potter and the Philosopher\'s Stone"

        val MOVIE_COLLECTION_ID = 1241
        val MOVIE_COLLECTION_TITLE = "Harry Potter Collection"

        val TVSHOW_TITLE = "Breaking Bad"
        val TVSHOW_ID = 1396
        val TVSHOW_IMDB_ID = "tt0903747"
        val TVSHOW_SEASON1_ID = 30272
        val EPISODE_IDMB_ID = "tt0959621"
        val PERSON_ID = 1
        val PERSON_NAME = "George Lucas"
        val PERSON_IMDB_ID = "nm0000184"
    }
}
