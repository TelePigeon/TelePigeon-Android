package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRoomIdUseCase
    @Inject
    constructor(
        private val telePigeonRepository: TelePigeonRepository,
    ) {
        operator fun invoke() = telePigeonRepository.getRoomId()
    }
