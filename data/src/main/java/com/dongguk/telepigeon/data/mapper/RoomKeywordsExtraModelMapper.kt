package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.request.RequestPutRoomKeywordsExtraDto
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel

fun RoomKeywordsExtraModel.toRequestPutRoomKeywordsExtraDto() =
    RequestPutRoomKeywordsExtraDto(
        keywords = this.keywords,
        gender = this.gender,
        ageRange = this.ageRange,
        relation = this.relation,
    )
