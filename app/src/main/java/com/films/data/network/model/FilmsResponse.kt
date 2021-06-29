package com.films.data.network.model

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("copyright") val copyright: String = "",
    @SerializedName("has_more") val hasMore: Boolean = false,
    @SerializedName("results") val results: List<FilmDto>?,
    @SerializedName("num_results") val numResults: Int = 0,
    @SerializedName("status") val status: String = "")