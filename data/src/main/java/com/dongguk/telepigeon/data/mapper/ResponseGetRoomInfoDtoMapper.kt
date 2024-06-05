package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomInfoDto
import com.dongguk.telepigeon.domain.model.RoomInfoModel

fun ResponseGetRoomInfoDto.toRoomInfoModel() = RoomInfoModel(
    code = this.code,
    name = this.name
)