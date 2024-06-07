package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.AuthKakaoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartKakaoLoginUseCase @Inject constructor(
    private val authKakaoRepository: AuthKakaoRepository
){
    operator fun invoke(postLogin: (String) -> Unit) = authKakaoRepository.startKakaoLogin(postLogin = postLogin)
}