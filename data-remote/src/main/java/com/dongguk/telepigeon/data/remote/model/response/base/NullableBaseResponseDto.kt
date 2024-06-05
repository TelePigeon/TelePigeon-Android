package com.dongguk.telepigeon.data.remote.model.response.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NullableBaseResponseDto<T>(
    @SerialName("code")
    val code: String,
    @SerialName("data")
    val data: T? = null,
    @SerialName("message")
    val message: String? = null,
)
