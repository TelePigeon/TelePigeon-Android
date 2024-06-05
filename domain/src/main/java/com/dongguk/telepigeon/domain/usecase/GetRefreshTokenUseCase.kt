package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class GetRefreshTokenUseCase(
    private val telePigeonRepository: TelePigeonRepository,
) {
    operator fun invoke() = telePigeonRepository.getRefreshToken()
}
