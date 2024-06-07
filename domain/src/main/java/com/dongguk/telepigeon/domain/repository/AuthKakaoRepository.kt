package com.dongguk.telepigeon.domain.repository

interface AuthKakaoRepository {
    fun startKakaoLogin(postLogin: (String) -> Unit)
}
