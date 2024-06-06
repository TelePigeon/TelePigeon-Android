package com.dongguk.telepigeon.feature.setting.keywordsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.KeywordExtraModel
import com.dongguk.telepigeon.domain.model.RoomExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.domain.model.RoomKeywordsModel
import com.dongguk.telepigeon.domain.usecase.GetAgeRangesUseCase
import com.dongguk.telepigeon.domain.usecase.GetGendersUseCase
import com.dongguk.telepigeon.domain.usecase.GetKeywordsUseCase
import com.dongguk.telepigeon.domain.usecase.GetRelationsUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomExtraUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomKeywordsUseCase
import com.dongguk.telepigeon.domain.usecase.PutRoomKeywordExtraUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeywordSettingViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val putRoomKeywordExtraUseCase: PutRoomKeywordExtraUseCase,
    private val getRoomExtraUseCase: GetRoomExtraUseCase,
    private val getKeywordsUseCase: GetKeywordsUseCase,
    private val getGendersUseCase: GetGendersUseCase,
    private val getAgeRangesUseCase: GetAgeRangesUseCase,
    private val getRelationsUseCase: GetRelationsUseCase
) : ViewModel() {
    private val _putRoomKeywordExtraState = MutableSharedFlow<UiState<Unit>>()
    val putRoomKeywordExtraState get() = _putRoomKeywordExtraState.asSharedFlow()

    private val _getRoomKeywordExtraState = MutableStateFlow<UiState<RoomExtraModel>>(UiState.Empty)
    val getRoomKeywordExtraState get() = _getRoomKeywordExtraState.asStateFlow()

    private val _getKeywordsState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val getKeywordsState get() = _getKeywordsState.asStateFlow()

    private val _getGendersState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val getGendersState get() = _getGendersState.asStateFlow()

    private val _getAgeRangesState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val getAgeRangesState get() = _getAgeRangesState.asStateFlow()

    private val _getRelationsState = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val getRelationsState get() = _getRelationsState.asStateFlow()

    private val roomId = getRoomIdUseCase()

    fun putRoomKeywordExtra(roomKeywordsExtraModel: RoomKeywordsExtraModel) {
        viewModelScope.launch {
            _putRoomKeywordExtraState.emit(UiState.Loading)
            putRoomKeywordExtraUseCase(roomId = roomId, roomKeywordsExtraModel = roomKeywordsExtraModel).onSuccess {
                _putRoomKeywordExtraState.emit(UiState.Success(Unit))
            }.onFailure { exception: Throwable ->
                _putRoomKeywordExtraState.emit(UiState.Error(exception.message))
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

    fun getKeywords() {
        viewModelScope.launch {
            _getKeywordsState.value = UiState.Loading
            getKeywordsUseCase().onSuccess { keywords ->
                _getKeywordsState.value = UiState.Success(keywords)
            }.onFailure { exception: Throwable ->
                _getKeywordsState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getGenders() {
        viewModelScope.launch {
            _getGendersState.value = UiState.Loading
            getGendersUseCase().onSuccess { genders ->
                _getGendersState.value = UiState.Success(genders)
            }.onFailure { exception: Throwable ->
                _getGendersState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getAgeRanges() {
        viewModelScope.launch {
            _getAgeRangesState.value = UiState.Loading
            getAgeRangesUseCase().onSuccess { ageRanges ->
                _getAgeRangesState.value = UiState.Success(ageRanges)
            }.onFailure { exception: Throwable ->
                _getAgeRangesState.value = UiState.Error(exception.message)
            }
        }
    }

    fun getRelations() {
        viewModelScope.launch {
            _getRelationsState.value = UiState.Loading
            getRelationsUseCase().onSuccess { relations ->
                _getRelationsState.value = UiState.Success(relations)
            }.onFailure { exception: Throwable ->
                _getRelationsState.value = UiState.Error(exception.message)
            }
        }
    }
}
