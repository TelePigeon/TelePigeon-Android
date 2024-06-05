package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetRoomKeywordsDto(
    @SerialName("keywords")
    val keywords: List<String>,
)
