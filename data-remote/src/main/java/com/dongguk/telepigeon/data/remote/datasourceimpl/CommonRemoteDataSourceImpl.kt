package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.CommonRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetAgeRangesDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetGendersDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRelationsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.service.CommonService
import javax.inject.Inject

class CommonRemoteDataSourceImpl @Inject constructor(
    private val commonService: CommonService
) : CommonRemoteDataSource {
    override suspend fun getKeywords(): BaseResponseDto<ResponseGetKeywordsDto> = commonService.getKeywords()

    override suspend fun getGenders(): BaseResponseDto<ResponseGetGendersDto> = commonService.getGenders()

    override suspend fun getAgeRanges(): BaseResponseDto<ResponseGetAgeRangesDto> = commonService.getAgeRanges()

    override suspend fun getRelations(): BaseResponseDto<ResponseGetRelationsDto> = commonService.getRelations()
}