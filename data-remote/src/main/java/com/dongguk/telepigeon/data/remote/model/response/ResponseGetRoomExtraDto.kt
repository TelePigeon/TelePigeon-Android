package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetRoomExtraDto(
    @SerialName("gender")
    val gender: String,
    @SerialName("ageRange")
    val ageRange: String,
    @SerialName("relation")
    val relation: String
)