package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.RoomRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(name: String) = roomRepository.postRoom(name = name)
}