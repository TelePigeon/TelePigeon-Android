package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Singleton

@Singleton
class SetRoomIdUseCase(
    private val telePigeonRepository: TelePigeonRepository
) {
    operator fun invoke(roomId: Int) = telePigeonRepository.setRoomId(roomId = roomId)
}