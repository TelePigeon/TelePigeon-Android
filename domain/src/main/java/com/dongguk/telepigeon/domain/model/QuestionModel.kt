package com.dongguk.telepigeon.domain.model

data class QuestionModel(
    val id: Int,
    val content: String,
    val isPenalty: Boolean,
    val easyMode: Boolean,
)
