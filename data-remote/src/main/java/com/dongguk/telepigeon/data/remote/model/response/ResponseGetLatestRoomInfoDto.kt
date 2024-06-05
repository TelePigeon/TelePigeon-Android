package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetLatestRoomInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: Int,
    @SerialName("days")
    val days: Int
)