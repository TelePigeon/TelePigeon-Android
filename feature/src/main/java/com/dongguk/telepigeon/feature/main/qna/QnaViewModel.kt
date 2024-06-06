package com.dongguk.telepigeon.feature.main.qna

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.CheckQuestionModel
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.domain.usecase.GetQuestionAnswerUseCase
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
    private val postAnswerUseCase: PostAnswerUseCase,
    private val getQuestionAnswerUseCase: GetQuestionAnswerUseCase,
) : ViewModel() {
    private val _getQuestionState = MutableStateFlow<UiState<CheckQuestionModel>>(UiState.Empty)
    val getQuestionState get() = _getQuestionState.asStateFlow()

    private val _postAnswerState = MutableSharedFlow<UiState<Unit>>()
    val postAnswerState get() = _postAnswerState.asSharedFlow()

    private val _getQuestionAnswerState = MutableStateFlow<UiState<List<QuestionAnswerModel>>>(UiState.Empty)
    val getQuestionAnswerState get() = _getQuestionAnswerState.asStateFlow()

    private val _imageUri = MutableStateFlow<String?>(null)
    val imageUri get() = _imageUri.asStateFlow()

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

    fun postAnswer(
        questionId: Int,
        image: String?,
        content: String,
    ) {
        viewModelScope.launch {
            _postAnswerState.emit(UiState.Loading)
            postAnswerUseCase(roomId = roomId, questionId = questionId, image = image, content = content).onSuccess {
                _postAnswerState.emit(UiState.Success(Unit))
            }.onFailure { exception: Throwable ->
                _postAnswerState.emit(UiState.Error(exception.message))
            }
        }
    }

    fun getQuestionAnswer() {
        viewModelScope.launch {
            _getQuestionAnswerState.value = UiState.Loading
            getQuestionAnswerUseCase(roomId = roomId, date = null, respondent = true).onSuccess { questionAnswerModels ->
                _getQuestionAnswerState.value = UiState.Success(questionAnswerModels)
            }.onFailure { exception: Throwable ->
                _getQuestionAnswerState.value = UiState.Error(exception.message)
            }
        }
    }

    fun setImageUri(uri: String?) {
        _imageUri.value = uri
    }
}
