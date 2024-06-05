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
    private val _postRoomState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val postRoomState get() = _postRoomState.asStateFlow()

    fun postRoom(name: String) {
        viewModelScope.launch {
            _postRoomState.value = UiState.Loading
            postRoomUseCase(name = name).onSuccess {
                _postRoomState.value = UiState.Success(Unit)
            }.onFailure { exception: Throwable ->
                _postRoomState.value = UiState.Error(exception.message)
            }
        }
    }
}