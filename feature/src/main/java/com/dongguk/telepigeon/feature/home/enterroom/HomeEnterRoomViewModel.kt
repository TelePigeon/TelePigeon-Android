package com.dongguk.telepigeon.feature.home.enterroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.usecase.PostEntranceRoomUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeEnterRoomViewModel @Inject constructor(
    private val postEntranceRoomUseCase: PostEntranceRoomUseCase
) : ViewModel() {
    private val _postEntranceState = MutableSharedFlow<UiState<String>>()
    val postEntranceState get() = _postEntranceState.asSharedFlow()

    fun postEntranceRoom(code: String) {
        viewModelScope.launch {
            _postEntranceState.emit(UiState.Loading)
            postEntranceRoomUseCase(code = code).onSuccess { code ->
                _postEntranceState.emit(UiState.Success(code))
            }.onFailure { exception: Throwable ->
                _postEntranceState.emit(UiState.Error(message = exception.message, code = (exception as? HttpException)?.response()?.code()))
            }
        }
    }
}