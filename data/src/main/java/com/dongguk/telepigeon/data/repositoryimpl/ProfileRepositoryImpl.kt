package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.mapper.toRequestPutRoomKeywordsExtraDto
import com.dongguk.telepigeon.data.mapper.toRoomKeywordExtraModel
import com.dongguk.telepigeon.data.mapper.toRoomKeywordModel
import com.dongguk.telepigeon.data.remote.datasource.ProfileRemoteDataSource
import com.dongguk.telepigeon.domain.model.RoomExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsModel
import com.dongguk.telepigeon.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl
    @Inject
    constructor(
        private val profileRemoteDataSource: ProfileRemoteDataSource,
    ) : ProfileRepository {
        override suspend fun getRoomKeywords(roomId: Int): Result<RoomKeywordsModel> =
            runCatching {
                profileRemoteDataSource.getRoomKeywords(roomId = roomId).data.toRoomKeywordModel()
            }

        override suspend fun getRoomExtra(roomId: Int): Result<RoomExtraModel> =
            runCatching {
                profileRemoteDataSource.getRoomExtra(roomId = roomId).data.toRoomKeywordExtraModel()
            }

        override suspend fun putRoomKeywordExtra(
            roomId: Int,
            roomKeywordsExtraModel: RoomKeywordsExtraModel,
        ): Result<Unit> =
            runCatching {
                profileRemoteDataSource.putRoomKeywordsExtra(roomId = roomId, requestPutRoomKeywordsExtraDto = roomKeywordsExtraModel.toRequestPutRoomKeywordsExtraDto())
            }
    }
