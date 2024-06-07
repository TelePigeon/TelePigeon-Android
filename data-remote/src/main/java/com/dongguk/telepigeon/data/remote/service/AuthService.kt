package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("$AUTH/$LOGIN/$KAKAO")
    suspend fun postLogin(
        @Header("$AUTHORIZATION") authorization: String,
        @Header("$FCM_TOKEN") fcmToken: String
    ): BaseResponseDto<ResponsePostLoginDto>

    @DELETE("$AUTH/$LOGOUT")
    suspend fun deleteLogout(): Response<Unit?>

    @DELETE("$AUTH/$WITHDRAWAL")
    suspend fun deleteWithdrawal(): Response<Unit?>

    @POST("$AUTH/$REISSUE")
    suspend fun postReissue(): NullableBaseResponseDto<Unit>

    companion object {
        const val AUTH = "auth"
        const val LOGIN = "login"
        const val KAKAO = "kakao"
        const val LOGOUT = "logout"
        const val WITHDRAWAL = "withdrawal"
        const val REISSUE = "reissue"
        const val AUTHORIZATION = "Authorization"
        const val FCM_TOKEN = "FcmToken"
    }
}
