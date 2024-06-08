package com.dongguk.telepigeon.interceptor

import android.app.Application
import com.dongguk.telepigeon.domain.usecase.GetAccessTokenUseCase
import com.dongguk.telepigeon.domain.usecase.GetIsLoginUseCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor(
        private val context: Application,
        private val getAccessTokenUseCase: GetAccessTokenUseCase,
        private val getIsLoginUseCase: GetIsLoginUseCase,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val authRequest =
                if (getIsLoginUseCase()) originalRequest.newAuthBuilder() else originalRequest
            val response = chain.proceed(authRequest)

            when (response.code) {
                CODE_TOKEN_EXPIRE -> {
                    // TODO 토큰 재발급 api 연동
                }
            }
            return response
        }

        private fun Request.newAuthBuilder() =
            this.newBuilder().addHeader(AUTHORIZATION, getAccessTokenUseCase()).build()

        companion object {
            const val CODE_TOKEN_EXPIRE = 401
            const val AUTHORIZATION = "Authorization"
            const val BEARER = "Bearer "
        }
    }
