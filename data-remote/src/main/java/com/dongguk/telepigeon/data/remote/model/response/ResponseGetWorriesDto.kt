package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetWorriesDto(
    @SerialName("worries")
    val worries: List<ResponseGetWorryDto>,
) {
    @Serializable
    data class ResponseGetWorryDto(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("content")
        val content: String,
        @SerialName("times")
        val times: List<String>,
    )
}
