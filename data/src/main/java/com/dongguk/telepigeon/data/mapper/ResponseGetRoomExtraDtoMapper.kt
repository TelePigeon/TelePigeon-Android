package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomExtraDto
import com.dongguk.telepigeon.domain.model.RoomExtraModel

fun ResponseGetRoomExtraDto.toRoomKeywordExtraModel() =
    RoomExtraModel(
        gender = this.gender,
        ageRange = this.ageRange,
        relation = this.relation,
    )
