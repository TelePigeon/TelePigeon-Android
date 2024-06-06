package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.HurryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostHurryUseCase
    @Inject
    constructor(
        private val hurryRepository: HurryRepository,
    ) {
        suspend operator fun invoke(roomId: Int) = hurryRepository.postHurry(roomId = roomId)
    }
