package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class SetRefreshTokenUseCase(
    private val telePigeonRepository: TelePigeonRepository
){
    operator fun invoke(refreshToken: String) = telePigeonRepository.setRefreshToken(refreshToken = refreshToken)
}