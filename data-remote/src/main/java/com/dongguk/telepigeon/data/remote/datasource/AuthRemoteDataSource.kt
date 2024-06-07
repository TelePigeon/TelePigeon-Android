package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto

interface AuthRemoteDataSource {
    suspend fun postLogin(
        authorization: String
    ): BaseResponseDto<ResponsePostLoginDto>
    suspend fun deleteLogin(): NullableBaseResponseDto<Unit>
    suspend fun deleteWithdrawal(): NullableBaseResponseDto<Unit>
    suspend fun postReissue(): NullableBaseResponseDto<Unit>
}