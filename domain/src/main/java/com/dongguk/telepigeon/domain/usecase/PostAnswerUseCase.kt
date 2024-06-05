package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostAnswerUseCase @Inject constructor(
    private val questionAnswerRepository: QuestionAnswerRepository
) {
    suspend operator fun invoke(roomId: Int, questionId: Int, image: String?, content: String) = questionAnswerRepository.postAnswer(roomId = roomId, questionId = questionId, image = image, content = content)
}