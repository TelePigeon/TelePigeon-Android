package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomKeywordsDto
import com.dongguk.telepigeon.domain.model.RoomKeywordModel

fun ResponseGetRoomKeywordsDto.toRoomKeywordModel() = RoomKeywordModel(
    keywords = this.keywords.joinToString { ", " }
)