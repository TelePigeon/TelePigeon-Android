package com.dongguk.telepigeon.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostWorryDto(
    @SerialName("name")
    val name: String,
    @SerialName("content")
    val content: String,
    @SerialName("times")
    val times: List<String>,
)
