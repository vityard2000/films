package com.films.data.network.model

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("multimedia") val multimedia: MultimediaDto,
    @SerializedName("date_updated") val dateUpdated: String = "",
    @SerializedName("display_title") val displayTitle: String = "",
    @SerializedName("link") val link: LinkDto,
    @SerializedName("publication_date") val publicationDate: String = "",
    @SerializedName("summary_short") val summaryShort: String = "",
    @SerializedName("critics_pick") val criticsPick: Int = 0,
    @SerializedName("byline") val byline: String = "",
    @SerializedName("headline") val headline: String = "",
    @SerializedName("mpaa_rating") val mpaaRating: String = "",
    @SerializedName("opening_date") val openingDate: String = "")