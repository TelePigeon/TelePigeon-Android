package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetLatestRoomInfoDto
import com.dongguk.telepigeon.domain.model.RoomModel

fun ResponseGetLatestRoomInfoDto.toRoomModel() =
    RoomModel(
        name = this.name,
        number = this.number,
        days = this.days,
    )
