package com.dongguk.telepigeon.domain.model

data class QuestionAnswerModel(
    val questionerName: String,
    val respondentName: String,
    val questionContent: String,
    val answerContent: String,
    val answerImage: String,
)
