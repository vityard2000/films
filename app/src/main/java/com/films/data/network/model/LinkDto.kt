package com.films.data.network.model

import com.google.gson.annotations.SerializedName

data class LinkDto(
    @SerializedName("suggested_link_text") val suggestedLinkText: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("url") val url: String = ""
)