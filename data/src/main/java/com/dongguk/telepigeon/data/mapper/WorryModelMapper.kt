package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.request.RequestPostWorryDto
import com.dongguk.telepigeon.domain.model.WorryModel

fun WorryModel.toRequestPostWorryDto() = RequestPostWorryDto(
    name = this.name,
    content = this.content,
    times = this.times.removePrefix(TIME).split(", ").toList()
)