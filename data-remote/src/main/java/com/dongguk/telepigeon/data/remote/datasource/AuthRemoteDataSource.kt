package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun postLogin(
        authorization: String,
        fcmToken: String
    ): BaseResponseDto<ResponsePostLoginDto>

    suspend fun deleteLogout(): Response<Unit?>

    suspend fun deleteWithdrawal(): Response<Unit?>

    suspend fun postReissue(): NullableBaseResponseDto<Unit>
}
