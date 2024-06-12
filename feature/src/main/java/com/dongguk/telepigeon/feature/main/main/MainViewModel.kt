package com.dongguk.telepigeon.feature.main.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.RoomModel
import com.dongguk.telepigeon.domain.usecase.GetLatestRoomInfoUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.PostHurryUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val getRoomIdUseCase: GetRoomIdUseCase,
        private val getLatestRoomInfoUseCase: GetLatestRoomInfoUseCase,
        private val postHurryUseCase: PostHurryUseCase,
    ) : ViewModel() {
        private val _getLatestRoomInfoState = MutableStateFlow<UiState<RoomModel>>(UiState.Empty)
        val getLatestRoomInfoState get() = _getLatestRoomInfoState.asStateFlow()

        private val _postHurryState = MutableSharedFlow<UiState<String>>()
        val postHurryState get() = _postHurryState.asSharedFlow()

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

        fun postHurry() {
            viewModelScope.launch {
                _postHurryState.emit(UiState.Loading)
                postHurryUseCase(roomId = roomId).onSuccess { code ->
                    _postHurryState.emit(UiState.Success(code))
                }.onFailure { exception: Throwable ->
                    _postHurryState.emit(UiState.Error(exception.message))
                }
            }
        }
    }
