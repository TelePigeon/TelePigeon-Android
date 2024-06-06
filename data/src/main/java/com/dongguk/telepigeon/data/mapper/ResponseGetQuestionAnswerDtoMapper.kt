package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionAnswerDto.ResponseGetQuestionAnswerSetDto
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel

fun ResponseGetQuestionAnswerSetDto.toQuestionAnswerModel() =
    QuestionAnswerModel(
        questionName = this.questionName,
        answerName = this.answerName,
        questionContent = this.questionContent,
        answerContent = this.answerContent,
        answerImage = this.answerImage,
    )
