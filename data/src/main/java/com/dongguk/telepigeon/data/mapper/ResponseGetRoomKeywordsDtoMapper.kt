package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomKeywordsDto
import com.dongguk.telepigeon.domain.model.RoomKeywordsModel

fun ResponseGetRoomKeywordsDto.toRoomKeywordModel() =
    RoomKeywordsModel(
        keywords = this.keywords.joinToString(", ") { it.trim() }
    )
