package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.ProfileRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.request.RequestPutRoomKeywordsExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.ProfileService
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRemoteDataSource {
    override suspend fun getRoomKeywords(roomId: Int): BaseResponseDto<ResponseGetRoomKeywordsDto> = profileService.getRoomKeywords(roomId = roomId)

    override suspend fun getRoomExtra(roomId: Int): BaseResponseDto<ResponseGetRoomExtraDto> = profileService.getRoomExtra(roomId = roomId)

    override suspend fun putRoomKeywordsExtra(roomId: Int, requestPutRoomKeywordsExtraDto: RequestPutRoomKeywordsExtraDto): NullableBaseResponseDto<Unit> = profileService.putRoomKeywordsExtra(roomId = roomId, requestPutRoomKeywordsExtraDto = requestPutRoomKeywordsExtraDto)
}