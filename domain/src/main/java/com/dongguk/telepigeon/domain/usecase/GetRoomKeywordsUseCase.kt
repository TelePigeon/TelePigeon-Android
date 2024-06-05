package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRoomKeywordsUseCase
    @Inject
    constructor(
        private val profileRepository: ProfileRepository,
    ) {
        suspend operator fun invoke(roomId: Int) = profileRepository.getRoomKeywords(roomId = roomId)
    }
