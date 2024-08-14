package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionDto
import com.dongguk.telepigeon.domain.model.QuestionModel

fun ResponseGetQuestionDto.toCheckQuestionModel() =
    QuestionModel(
        id = this.id,
        content = this.content,
        isPenalty = this.isPenalty,
        easyMode = this.easyMode,
    )
