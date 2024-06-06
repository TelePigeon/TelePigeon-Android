package com.dongguk.telepigeon.feature.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.domain.usecase.GetRoomsUseCase
import com.dongguk.telepigeon.domain.usecase.SetRoomIdUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val setRoomIdUseCase: SetRoomIdUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
) : ViewModel() {
    private val _getRoomsState = MutableStateFlow<UiState<List<HomeRoomModel>>>(UiState.Empty)
    val getRoomsState get() = _getRoomsState.asStateFlow()

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

    fun setRoomId(roomId: Int) {
        setRoomIdUseCase(roomId = roomId)
    }
}
