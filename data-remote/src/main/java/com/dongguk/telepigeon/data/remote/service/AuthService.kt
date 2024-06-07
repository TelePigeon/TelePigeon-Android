package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthService {
    @POST("$AUTH/$LOGIN/$KAKAO")
    suspend fun postLogin():BaseResponseDto<ResponsePostLoginDto>

    @DELETE("$AUTH/$LOGOUT")
    suspend fun deleteLogout(): NullableBaseResponseDto<Unit>

    @DELETE("$AUTH/$WITHDRAWAL")
    suspend fun deleteWithdrawal(): NullableBaseResponseDto<Unit>

    @POST("$AUTH/$REISSUE")
    suspend fun postReissue(): NullableBaseResponseDto<Unit>

    companion object {
        const val AUTH = "auth"
        const val LOGIN = "login"
        const val KAKAO = "kakao"
        const val LOGOUT = "logout"
        const val WITHDRAWAL = "withdrawal"
        const val REISSUE = "reissue"
    }
}