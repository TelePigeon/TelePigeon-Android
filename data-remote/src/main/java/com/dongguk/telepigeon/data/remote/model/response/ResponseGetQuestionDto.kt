package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetQuestionDto(
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
    @SerialName("isPenalty")
    val isPenalty: Boolean
)