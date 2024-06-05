package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class SetIsLoginUseCase(
    private val telePigeonRepository: TelePigeonRepository,
) {
    operator fun invoke(isLogin: Boolean) = telePigeonRepository.setIsLogin(isLogin = isLogin)
}
