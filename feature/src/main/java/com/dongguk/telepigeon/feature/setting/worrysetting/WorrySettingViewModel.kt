package com.dongguk.telepigeon.feature.setting.worrysetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.domain.usecase.DeleteWorryUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.GetWorriesUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorrySettingViewModel
    @Inject
    constructor(
        private val getRoomIdUseCase: GetRoomIdUseCase,
        private val getWorriesUseCase: GetWorriesUseCase,
        private val deleteWorryUseCase: DeleteWorryUseCase,
    ) : ViewModel() {
        private val _getWorriesState = MutableStateFlow<UiState<List<RoomWorryModel>>>(UiState.Empty)
        val getWorriesState get() = _getWorriesState.asStateFlow()

        private val _deleteWorryState = MutableSharedFlow<UiState<Unit>>()
        val deleteWorryState get() = _deleteWorryState.asSharedFlow()

        private val roomId = getRoomIdUseCase()

        fun getWorries() {
            viewModelScope.launch {
                _getWorriesState.value = UiState.Loading
                getWorriesUseCase(roomId = roomId).onSuccess { roomWorryModels ->
                    _getWorriesState.value = UiState.Success(roomWorryModels)
                }.onFailure { exception: Throwable ->
                    _getWorriesState.value = UiState.Error(exception.message)
                }
            }
        }

        fun deleteWorry(worryId: Int) {
            viewModelScope.launch {
                _deleteWorryState.emit(UiState.Loading)
                deleteWorryUseCase(worryId = worryId).onSuccess {
                    _deleteWorryState.emit(UiState.Success(Unit))
                }.onFailure { exception: Throwable ->
                    _deleteWorryState.emit(UiState.Error(exception.message))
                }
            }
        }
    }
