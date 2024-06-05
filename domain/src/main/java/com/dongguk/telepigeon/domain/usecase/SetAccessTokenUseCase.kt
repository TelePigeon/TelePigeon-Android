package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class SetAccessTokenUseCase (
    private val telePigeonRepository: TelePigeonRepository
){
    operator fun invoke(accessToken: String) = telePigeonRepository.setAccessToken(accessToken = accessToken)
}