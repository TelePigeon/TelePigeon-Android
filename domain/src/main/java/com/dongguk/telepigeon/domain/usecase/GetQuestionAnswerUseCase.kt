package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuestionAnswerUseCase
    @Inject
    constructor(
        private val questionAnswerRepository: QuestionAnswerRepository,
    ) {
        suspend operator fun invoke(
            roomId: Int,
            date: String?,
            respondent: Boolean,
        ) = questionAnswerRepository.getQuestionAnswer(roomId = roomId, date = date, respondent = respondent)
    }
