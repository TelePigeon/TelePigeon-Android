package com.dongguk.telepigeon.feature.main.qna

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.CheckQuestionModel
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.domain.usecase.GetQuestionUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.PostAnswerUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnaViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val postAnswerUseCase: PostAnswerUseCase
) : ViewModel() {
    private val _getQuestionState = MutableStateFlow<UiState<CheckQuestionModel>>(UiState.Empty)
    val getQuestionState get() = _getQuestionState.asStateFlow()

    private val _postAnswerState = MutableSharedFlow<UiState<Unit>>()
    val postAnswerState get() = _postAnswerState.asSharedFlow()

    private val roomId = getRoomIdUseCase()

    fun getQuestion() {
        viewModelScope.launch {
            _getQuestionState.value = UiState.Loading
            getQuestionUseCase(roomId = roomId).onSuccess { checkQuestionModel ->
                _getQuestionState.value = UiState.Success(checkQuestionModel)
            }.onFailure { exception: Throwable ->
                _getQuestionState.value = UiState.Error(exception.message)
            }
        }
    }

    fun postAnswer(questionId: Int, image: String?, content: String) {
        viewModelScope.launch {
            _postAnswerState.emit(UiState.Loading)
            postAnswerUseCase(roomId = roomId, questionId = questionId, image = image, content = content).onSuccess {
                _postAnswerState.emit(UiState.Success(Unit))
            }.onFailure { exception: Throwable ->
                _postAnswerState.emit(UiState.Error(exception.message))
            }
        }
    }

    val dummyQuestionAnswerModel =
        QuestionAnswerModel(
            questionName = "김둘기",
            answerName = "둘기맘",
            questionContent = "오늘 점심은 무슨 음식을 먹었나요?",
            answerContent = "분식을 먹었어요! 맛있었어요!",
            answerImage = "https://avatars.githubusercontent.com/u/103172971?v=4",
        )
}
