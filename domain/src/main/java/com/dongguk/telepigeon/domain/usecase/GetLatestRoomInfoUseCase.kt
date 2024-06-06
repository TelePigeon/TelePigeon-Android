package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLatestRoomInfoUseCase
    @Inject
    constructor(
        private val questionAnswerRepository: QuestionAnswerRepository,
    ) {
        suspend operator fun invoke(roomId: Int) = questionAnswerRepository.getLatestRoomInfo(roomId = roomId)
    }
