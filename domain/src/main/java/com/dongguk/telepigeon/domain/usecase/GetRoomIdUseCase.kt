package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class GetRoomIdUseCase(
    private val telePigeonRepository: TelePigeonRepository,
) {
    operator fun invoke() = telePigeonRepository.getRoomId()
}
