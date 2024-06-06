package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import javax.inject.Singleton

@Singleton
class GetQuestionAnswerUseCase(
    private val questionAnswerRepository: QuestionAnswerRepository,
) {
    suspend operator fun invoke(
        roomId: Int,
        date: String?,
        respondent: Boolean,
    ) = questionAnswerRepository.getQuestionAnswer(roomId = roomId, date = date, respondent = respondent)
}
