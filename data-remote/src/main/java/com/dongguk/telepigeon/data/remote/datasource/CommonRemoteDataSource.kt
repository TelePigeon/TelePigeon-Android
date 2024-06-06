package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetAgeRangesDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetGendersDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRelationsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto

interface CommonRemoteDataSource {
    suspend fun getKeywords(): BaseResponseDto<ResponseGetKeywordsDto>
    suspend fun getGenders(): BaseResponseDto<ResponseGetGendersDto>
    suspend fun getAgeRanges(): BaseResponseDto<ResponseGetAgeRangesDto>
    suspend fun getRelations(): BaseResponseDto<ResponseGetRelationsDto>
}