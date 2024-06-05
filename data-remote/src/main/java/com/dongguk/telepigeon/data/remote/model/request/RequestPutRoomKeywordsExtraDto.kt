package com.dongguk.telepigeon.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPutRoomKeywordsExtraDto(
    @SerialName("keywords")
    val keywords: List<String>,
    @SerialName("gender")
    val gender: String,
    @SerialName("ageRange")
    val ageRange: String,
    @SerialName("relation")
    val relation: String,
)
