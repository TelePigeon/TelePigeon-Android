package com.dongguk.telepigeon.feature.setting.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.RoomExtraModel
import com.dongguk.telepigeon.domain.model.RoomInfoModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsModel
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.domain.usecase.GetRoomExtraUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomInfoUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomKeywordsUseCase
import com.dongguk.telepigeon.domain.usecase.GetWorriesUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val getRoomInfoUseCase: GetRoomInfoUseCase,
    private val getRoomKeywordsUseCase: GetRoomKeywordsUseCase,
    private val getRoomExtraUseCase: GetRoomExtraUseCase,
    private val getWorriesUseCase: GetWorriesUseCase
) : ViewModel() {
    private val _getRoomInfoState = MutableStateFlow<UiState<RoomInfoModel>>(UiState.Empty)
    val getRoomInfoState get() = _getRoomInfoState.asStateFlow()

    private val _getRoomKeywordsState = MutableStateFlow<UiState<RoomKeywordsModel>>(UiState.Empty)
    val getRoomKeywordsState get() = _getRoomKeywordsState.asStateFlow()

    private val _getRoomKeywordExtraState = MutableStateFlow<UiState<RoomExtraModel>>(UiState.Empty)
    val getRoomKeywordExtraState get() = _getRoomKeywordExtraState.asStateFlow()

    private val _getWorriesState = MutableStateFlow<UiState<List<RoomWorryModel>>>(UiState.Empty)
    val getWorriesState get() = _getWorriesState.asStateFlow()

    private val roomId = getRoomIdUseCase()

    fun getRoomInfo() {
        viewModelScope.launch {
            _getRoomInfoState.value = UiState.Loading
            getRoomInfoUseCase(roomId = roomId).onSuccess { roomInfoModel ->
                _getRoomInfoState.value = UiState.Success(roomInfoModel)
            }.onFailure { exception: Throwable ->
                _getRoomInfoState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getRoomKeywords() {
        viewModelScope.launch {
            _getRoomKeywordsState.value = UiState.Loading
            getRoomKeywordsUseCase(roomId = roomId).onSuccess { roomKeywordsModel ->
                _getRoomKeywordsState.value = UiState.Success(roomKeywordsModel)
            }.onFailure { exception: Throwable ->
                _getRoomKeywordsState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getRoomKeywordExtra() {
        viewModelScope.launch {
            _getRoomKeywordExtraState.value = UiState.Loading
            getRoomExtraUseCase(roomId = roomId).onSuccess { roomExtraModel ->
                _getRoomKeywordExtraState.value = UiState.Success(roomExtraModel)
            }.onFailure { exception: Throwable ->
                _getRoomKeywordExtraState.value = UiState.Error(exception.message)
            }
        }
    }

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
}
