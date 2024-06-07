package com.dongguk.telepigeon.feature.home.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.usecase.ClearTelePigeonLocalDataUseCase
import com.dongguk.telepigeon.domain.usecase.DeleteLogoutUseCase
import com.dongguk.telepigeon.domain.usecase.DeleteWithdrawalUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSettingViewModel
    @Inject
    constructor(
        private val deleteWithdrawalUseCase: DeleteWithdrawalUseCase,
        private val deleteLogoutUseCase: DeleteLogoutUseCase,
        private val clearTelePigeonLocalDataUseCase: ClearTelePigeonLocalDataUseCase,
    ) : ViewModel() {
        private val _deleteWithdrawalState = MutableSharedFlow<UiState<Unit>>()
        val deleteWithdrawalState get() = _deleteWithdrawalState.asSharedFlow()

        private val _deleteLogoutState = MutableSharedFlow<UiState<Unit>>()
        val deleteLogoutState get() = _deleteLogoutState.asSharedFlow()

        fun deleteWithdrawal() {
            viewModelScope.launch {
                _deleteWithdrawalState.emit(UiState.Loading)
                deleteWithdrawalUseCase().onSuccess {
                    _deleteWithdrawalState.emit(UiState.Success(Unit))
                    clearTelePigeonLocalDataUseCase()
                }.onFailure { exception: Throwable ->
                    _deleteWithdrawalState.emit(UiState.Error(exception.message))
                }
            }
        }

        fun deleteLogout() {
            viewModelScope.launch {
                _deleteLogoutState.emit(UiState.Loading)
                deleteLogoutUseCase().onSuccess {
                    _deleteLogoutState.emit(UiState.Success(Unit))
                    clearTelePigeonLocalDataUseCase()
                }.onFailure { exception: Throwable ->
                    _deleteLogoutState.emit(UiState.Error(exception.message))
                }
            }
        }
    }
