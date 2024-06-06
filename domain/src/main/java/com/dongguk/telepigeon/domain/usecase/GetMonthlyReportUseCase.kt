package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMonthlyReportUseCase
    @Inject
    constructor(
        private val questionAnswerRepository: QuestionAnswerRepository,
    ) {
        suspend operator fun invoke(
            roomId: Int,
            date: String,
        ) = questionAnswerRepository.getMonthlyReport(roomId = roomId, date = date)
    }
