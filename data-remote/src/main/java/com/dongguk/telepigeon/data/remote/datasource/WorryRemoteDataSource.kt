package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.request.RequestPostWorryDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetWorriesDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto

interface WorryRemoteDataSource {
    suspend fun getWorries(roomId: Int): BaseResponseDto<ResponseGetWorriesDto>

    suspend fun postWorry(
        roomId: Int,
        requestPostWorryDto: RequestPostWorryDto,
    ): NullableBaseResponseDto<Unit>

    suspend fun deleteWorry(worryId: Int): Unit?
}
