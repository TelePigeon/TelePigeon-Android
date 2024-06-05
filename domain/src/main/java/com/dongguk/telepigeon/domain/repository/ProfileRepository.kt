package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.RoomExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel

interface ProfileRepository {
    suspend fun getRoomKeywords(roomId: Int): Result<RoomKeywordModel>
    suspend fun getRoomExtra(roomId: Int): Result<RoomExtraModel>
    suspend fun putRoomKeywordExtra(roomId: Int, roomKeywordsExtraModel: RoomKeywordsExtraModel): Result<Unit>
}