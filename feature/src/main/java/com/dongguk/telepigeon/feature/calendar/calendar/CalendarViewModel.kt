package com.dongguk.telepigeon.feature.calendar.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.domain.usecase.GetQuestionAnswerUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val getQuestionAnswerUseCase: GetQuestionAnswerUseCase
) : ViewModel() {
    private val _getQuestionAnswerState = MutableStateFlow<UiState<List<QuestionAnswerModel>>>(UiState.Empty)
    val getQuestionAnswerState get() = _getQuestionAnswerState.asStateFlow()

    private val roomId = getRoomIdUseCase()

    fun getQuestionAnswer(date: String) {
        viewModelScope.launch {
            _getQuestionAnswerState.value = UiState.Loading
            getQuestionAnswerUseCase(roomId = roomId, date = date, respondent = false).onSuccess { questionAnswerModels ->
                _getQuestionAnswerState.value = UiState.Success(questionAnswerModels)
            }.onFailure { exception: Throwable ->
                _getQuestionAnswerState.value = UiState.Error(exception.message)
            }
        }
    }
}
