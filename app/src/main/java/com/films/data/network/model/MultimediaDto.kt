package com.films.data.network.model

import com.google.gson.annotations.SerializedName

data class MultimediaDto(
    @SerializedName("src") val src: String = "",
    @SerializedName("width") val width: Int = 0,
    @SerializedName("type") val type: String = "",
    @SerializedName("height") val height: Int = 0
)