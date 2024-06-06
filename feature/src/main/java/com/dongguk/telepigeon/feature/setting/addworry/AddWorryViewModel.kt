package com.dongguk.telepigeon.feature.setting.addworry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.WorryModel
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telepigeon.domain.usecase.PostWorryUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWorryViewModel
    @Inject
    constructor(
        private val getRoomIdUseCase: GetRoomIdUseCase,
        private val postWorryUseCase: PostWorryUseCase,
    ) : ViewModel() {
        private val _postWorryState = MutableSharedFlow<UiState<Unit>>()
        val postWorryState get() = _postWorryState.asSharedFlow()

        private val roomId = getRoomIdUseCase()

        fun postWorry(worryModel: WorryModel) {
            viewModelScope.launch {
                _postWorryState.emit(UiState.Empty)
                postWorryUseCase(roomId = roomId, worryModel = worryModel).onSuccess {
                    _postWorryState.emit(UiState.Success(Unit))
                }.onFailure { exception: Throwable ->
                    _postWorryState.emit(UiState.Error(exception.message))
                }
            }
        }
    }
