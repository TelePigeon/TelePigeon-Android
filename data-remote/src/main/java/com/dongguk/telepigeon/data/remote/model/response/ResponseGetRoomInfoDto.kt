package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetRoomInfoDto(
    @SerialName("code")
    val code: String,
    @SerialName("name")
    val name: String,
)
