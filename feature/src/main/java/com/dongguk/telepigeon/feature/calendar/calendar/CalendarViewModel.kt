package com.dongguk.telepigeon.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    val dummyQuestionAnswerModels = listOf(
        QuestionAnswerModel(
            questionerName = "김둘기",
            answerName = "둘기맘",
            questionContent = "오늘 점심은 무슨 음식을 먹었나요?",
            answerContent = "분식을 먹었어요! 맛있었어요!",
            answerImage = "https://avatars.githubusercontent.com/u/103172971?v=4",
        ),
        QuestionAnswerModel(
            questionerName = "둘기맘",
            answerName = "김둘기",
            questionContent = "영양제는 잘 챙겨먹고 있나요?",
            answerContent = "어제는 까먹었어요.. 오늘은 꼭 먹을게요!",
            answerImage = "https://avatars.githubusercontent.com/u/103172971?v=4",
        )
    )
}