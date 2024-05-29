package com.dongguk.telepigeon.domain.model

data class CheckQuestionModel(
    val id: Int,
    val content: String,
    val isPenalty: Boolean,
)
