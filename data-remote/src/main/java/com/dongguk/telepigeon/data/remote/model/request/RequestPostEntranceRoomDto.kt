package com.dongguk.telepigeon.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostEntranceRoomDto(
    @SerialName("code")
    val code: String
)