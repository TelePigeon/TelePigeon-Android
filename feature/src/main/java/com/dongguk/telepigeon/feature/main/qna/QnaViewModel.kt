package com.dongguk.telepigeon.feature.main.qna

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.CheckQuestionEntity
import com.dongguk.telepigeon.domain.model.QuestionAnswerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QnaViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyCheckQuestionEntity =
            CheckQuestionEntity(
                id = 1,
                content = "오늘 점심은 무슨 음식을 먹었나요?",
                isPenalty = true,
            )

        val dummyQuestionAnswerEntity =
            QuestionAnswerEntity(
                questionerName = "김둘기",
                respondentName = "둘기맘",
                questionContent = "오늘 점심은 무슨 음식을 먹었나요?",
                answerContent = "분식을 먹었어요! 맛있었어요!",
                answerImage = "https://avatars.githubusercontent.com/u/103172971?v=4",
            )
    }
