package com.uwetrottmann.tmdb2.enumerations

enum class ReleaseType constructor(val id: Int) {

    PREMIERE(1),
    THEATRICAL_LIMITED(2),
    THEATRICAL(3),
    DIGITAL(4),
    PHYSICAL(5),
    TV(6)
}
