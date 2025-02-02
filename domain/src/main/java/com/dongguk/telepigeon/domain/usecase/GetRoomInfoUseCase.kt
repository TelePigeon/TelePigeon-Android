package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.RoomRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRoomInfoUseCase
    @Inject
    constructor(
        private val roomRepository: RoomRepository,
    ) {
        suspend operator fun invoke(roomId: Int) = roomRepository.getRoomInfo(roomId = roomId)
    }
