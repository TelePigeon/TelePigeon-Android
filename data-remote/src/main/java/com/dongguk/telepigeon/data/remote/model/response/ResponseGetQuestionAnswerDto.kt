package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetQuestionAnswerDto(
    @SerialName("sets")
    val sets: List<ResponseGetQuestionAnswerSetDto>
) {
    @Serializable
    data class ResponseGetQuestionAnswerSetDto(
        @SerialName("questionName")
        val questionName: String,
        @SerialName("answerName")
        val answerName: String,
        @SerialName("questionContent")
        val questionContent: String,
        @SerialName("answerContent")
        val answerContent: String,
        @SerialName("answerImage")
        val answerImage: String?
    )
}