package com.dongguk.telepigeon.data.remote.service

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthKakaoService
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
        private val userApiClient: UserApiClient,
    ) {
        fun startKakaoLogin(
            postLogin: (String) -> Unit,
        ) {
            val callback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, _ ->
                if (oAuthToken != null) {
                    postLogin(oAuthToken.accessToken)
                }
            }

            if (userApiClient.isKakaoTalkLoginAvailable(context)) {
                userApiClient.loginWithKakaoTalk(context, callback = callback)
            } else {
                userApiClient.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }
