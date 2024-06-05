package com.dongguk.telepigeon.data.remote.model.response.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NullableBaseResponseDto<T>(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: T? = null
)