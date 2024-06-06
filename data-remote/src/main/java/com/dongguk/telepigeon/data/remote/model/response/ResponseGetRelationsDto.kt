package com.dongguk.telepigeon.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetRelationsDto(
    @SerialName("relations")
    val relations: List<String>,
)
