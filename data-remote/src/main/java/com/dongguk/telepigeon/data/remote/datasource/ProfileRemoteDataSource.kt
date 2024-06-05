package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.request.RequestPutRoomKeywordsExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto

interface ProfileRemoteDataSource {
    suspend fun getRoomKeywords(roomId: Int): BaseResponseDto<ResponseGetRoomKeywordsDto>
    suspend fun getRoomExtra(roomId: Int): BaseResponseDto<ResponseGetRoomExtraDto>
    suspend fun putRoomKeywordsExtra(roomId: Int, requestPutRoomKeywordsExtraDto: RequestPutRoomKeywordsExtraDto): NullableBaseResponseDto<Unit>
}