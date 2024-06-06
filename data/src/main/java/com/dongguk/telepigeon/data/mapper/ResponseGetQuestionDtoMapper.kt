package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionDto
import com.dongguk.telepigeon.domain.model.CheckQuestionModel

fun ResponseGetQuestionDto.toCheckQuestionModel() =
    CheckQuestionModel(
        id = this.id,
        content = this.content,
        isPenalty = this.isPenalty,
    )
