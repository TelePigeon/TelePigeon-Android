package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.remote.service.AuthKakaoService
import com.dongguk.telepigeon.domain.repository.AuthKakaoRepository
import javax.inject.Inject

class AuthKakaoRepositoryImpl
    @Inject
    constructor(
        private val authKakaoService: AuthKakaoService,
    ) : AuthKakaoRepository {
        override fun startKakaoLogin(postLogin: (String) -> Unit) = authKakaoService.startKakaoLogin(postLogin = postLogin)
    }
