package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetAgeRangesDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetGendersDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRelationsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import retrofit2.http.GET

interface CommonService {
    @GET(KEYWORDS)
    suspend fun getKeywords():BaseResponseDto<ResponseGetKeywordsDto>

    @GET(GENDERS)
    suspend fun getGenders():BaseResponseDto<ResponseGetGendersDto>

    @GET(AGE_RANGES)
    suspend fun getAgeRanges():BaseResponseDto<ResponseGetAgeRangesDto>

    @GET(RELATIONS)
    suspend fun getRelations():BaseResponseDto<ResponseGetRelationsDto>

    companion object {
        const val KEYWORDS = "keywords"
        const val GENDERS = "genders"
        const val AGE_RANGES = "age-ranges"
        const val RELATIONS = "relations"
    }
}