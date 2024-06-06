package com.dongguk.telepigeon.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val authRequest =

                originalRequest.newBuilder().addHeader(AUTHORIZATION, "Bearer eyJKV1QiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsImlhdCI6MTcxNzY1NTQ1MCwiZXhwIjoxNzE4ODY1MDUwfQ.Yzsslyb6Iait3Pa2Uxfuuvk7mC7oxCjgxg_KOVDqLLar436EcgRwY7BJfXRrTeNqnF7mUMsf9S-zM017IOs68Q").build()
            val response = chain.proceed(authRequest)

            when (response.code) {
                401 -> {
                    // TODO 토큰 재발급 api 연동
                }
            }
            return response
        }

        companion object {
            const val AUTHORIZATION = "Authorization"
        }
    }
