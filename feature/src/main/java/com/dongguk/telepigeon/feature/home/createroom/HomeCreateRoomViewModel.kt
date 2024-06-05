package com.dongguk.telepigeon.feature.home.createroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.usecase.PostRoomUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeCreateRoomViewModel @Inject constructor(
    private val postRoomUseCase: PostRoomUseCase
) : ViewModel() {
    private val _postRoomState = MutableSharedFlow<UiState<Unit>>()
    val postRoomState get() = _postRoomState.asSharedFlow()

    fun postRoom(name: String) {
        viewModelScope.launch {
            _postRoomState.emit(UiState.Loading)
            postRoomUseCase(name = name).onSuccess {
                _postRoomState.emit(UiState.Success(Unit))
            }.onFailure { exception: Throwable ->
                _postRoomState.emit(UiState.Error(exception.message))
            }
        }
    }
}