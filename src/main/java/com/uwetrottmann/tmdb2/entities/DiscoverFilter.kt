package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.enumerations.ReleaseType

class DiscoverFilter(
        val separator: Separator,
        val items: IntArray
) {
    enum class Separator(val symbol: String) {
        AND(","), OR("|")
    }

    constructor(vararg items: Int) : this(
        separator = Separator.AND, items = items.toTypedArray().toIntArray()
    )

    constructor(separator: Separator, vararg types: ReleaseType) : this(
        separator = separator,
        items = IntArray(types.size).also {
            for (i in types.indices) {
                it[i] = types[i].id
            }
        }
    )

    override fun toString(): String {
        if (items.isEmpty()) {
            return ""
        }

        val sb = StringBuilder()
        for (item in items) {
            if (sb.isNotEmpty()) {
                sb.append(separator.symbol)
            }
            sb.append(item)
        }

        return sb.toString()
    }
}
