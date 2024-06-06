package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.WorryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWorriesUseCase
    @Inject
    constructor(
        private val worryRepository: WorryRepository,
    ) {
        suspend operator fun invoke(roomId: Int) = worryRepository.getWorries(roomId = roomId)
    }
