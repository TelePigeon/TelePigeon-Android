package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMonthlyReportDto(
    @SerialName("positiveKeywords")
    val positiveKeywords: List<String>,
    @SerialName("negativeKeywords")
    val negativeKeywords: List<String>,
)
