package com.dongguk.telepigeon.feature.main.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.RoomModel
import com.dongguk.telepigeon.domain.usecase.GetLatestRoomInfoUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val getLatestRoomInfoUseCase: GetLatestRoomInfoUseCase
) : ViewModel() {
    private val _getLatestRoomInfoState = MutableStateFlow<UiState<RoomModel>>(UiState.Empty)
    val getLatestRoomInfoState get() = _getLatestRoomInfoState.asStateFlow()

    private val roomId = getRoomIdUseCase()

    fun getLatestRoomInfo() {
        viewModelScope.launch {
            _getLatestRoomInfoState.value = UiState.Loading
            getLatestRoomInfoUseCase(roomId = roomId).onSuccess { roomModel ->
                _getLatestRoomInfoState.value = UiState.Success(roomModel)
            }.onFailure { exception: Throwable ->
                _getLatestRoomInfoState.value = UiState.Error(exception.message)
            }
        }
    }

}
