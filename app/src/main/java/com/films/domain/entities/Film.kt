package com.films.domain.entities



import java.io.Serializable

data class Film(
    val displayTitle: String? = "",
    val link: Link? = Link(),
    val multimedia: Multimedia? = Multimedia(),
    val dateUpdated: String? = "",
    val publicationDate: String? = "",
    val summaryShort: String? = "",
    val criticsPick: Int? = 0,
    val byline: String? = "",
    val headline: String? = "",
    val mpaaRating: String? = "",
    val openingDate: String? = ""
): Serializable
