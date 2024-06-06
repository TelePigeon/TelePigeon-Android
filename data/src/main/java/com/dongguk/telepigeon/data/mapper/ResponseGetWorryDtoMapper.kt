package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetWorriesDto.ResponseGetWorryDto
import com.dongguk.telepigeon.domain.model.RoomWorryModel

fun ResponseGetWorryDto.toRoomWorryModel() = RoomWorryModel(
    id = this.id,
    name = this.name,
    content = this.content,
    times = TIME + this.times.joinToString(", ")
)

const val TIME = "매일 "