package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.WorryRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.request.RequestPostWorryDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetWorriesDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.WorryService
import javax.inject.Inject

class WorryRemoteDataSourceImpl
    @Inject
    constructor(
        private val worryService: WorryService,
    ) : WorryRemoteDataSource {
        override suspend fun getWorries(roomId: Int): BaseResponseDto<ResponseGetWorriesDto> = worryService.getWorries(roomId = roomId)

        override suspend fun postWorry(
            roomId: Int,
            requestPostWorryDto: RequestPostWorryDto,
        ): NullableBaseResponseDto<Unit> = worryService.postWorry(roomId = roomId, requestPostWorryDto = requestPostWorryDto)

        override suspend fun deleteWorry(worryId: Int): NullableBaseResponseDto<Unit> = worryService.deleteWorry(worryId = worryId)
    }
