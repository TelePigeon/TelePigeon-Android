package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.AuthRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.AuthService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun postLogin(authorization: String): BaseResponseDto<ResponsePostLoginDto> = authService.postLogin(authorization = authorization)

    override suspend fun deleteLogin(): NullableBaseResponseDto<Unit> = authService.deleteLogout()

    override suspend fun deleteWithdrawal(): NullableBaseResponseDto<Unit> = authService.deleteWithdrawal()

    override suspend fun postReissue(): NullableBaseResponseDto<Unit> = authService.postReissue()
}