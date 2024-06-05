package com.dongguk.telepigeon.feature.home.modifyroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.domain.usecase.DeleteRoomUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomsUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeModifyRoomViewModel
    @Inject
    constructor(
        private val getRoomsUseCase: GetRoomsUseCase,
        private val deleteRoomUseCase: DeleteRoomUseCase,
    ) : ViewModel() {
        private val _getRoomsState = MutableStateFlow<UiState<List<HomeRoomModel>>>(UiState.Empty)
        val getRoomsState get() = _getRoomsState.asStateFlow()

        private val _deleteRoomState = MutableSharedFlow<UiState<Unit>>()
        val deleteRoomState get() = _deleteRoomState.asSharedFlow()

        fun getRooms() {
            viewModelScope.launch {
                _getRoomsState.value = UiState.Loading
                getRoomsUseCase().onSuccess { homeRoomModels ->
                    _getRoomsState.value = UiState.Success(homeRoomModels)
                }.onFailure { exception: Throwable ->
                    _getRoomsState.value = UiState.Error(exception.message)
                }
            }
        }

        fun deleteRoom(roomId: Int) {
            viewModelScope.launch {
                _deleteRoomState.emit(UiState.Loading)
                deleteRoomUseCase(roomId = roomId).onSuccess {
                    _deleteRoomState.emit(UiState.Success(Unit))
                }.onFailure { exception: Throwable ->
                    _deleteRoomState.emit(UiState.Error(exception.message))
                }
            }
        }
    }
