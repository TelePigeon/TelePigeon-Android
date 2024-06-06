package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.model.WorryModel
import com.dongguk.telepigeon.domain.repository.WorryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostWorryUseCase
    @Inject
    constructor(
        private val worryRepository: WorryRepository,
    ) {
        suspend operator fun invoke(
            roomId: Int,
            worryModel: WorryModel,
        ) = worryRepository.postWorry(roomId = roomId, worryModel = worryModel)
    }
