package com.dongguk.telepigeon.domain.model

data class CheckQuestionEntity(
    val id: Int,
    val content: String,
    val isPenalty: Boolean,
)
