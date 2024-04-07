package com.dongguk.telepigeon.feature.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.UserEntity
import com.dongguk.telepigeon.domain.usecase.GetDummyUserListUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DummyViewModel(
    private val getDummyUserListUseCase: GetDummyUserListUseCase
) : ViewModel() {
    private val _dummyUserListState = MutableStateFlow<UiState<List<UserEntity>>>(UiState.Empty)
    val dummyUserState = _dummyUserListState.asStateFlow()

    fun getDummyUserList() {
        viewModelScope.launch {
            viewModelScope.launch {
                _dummyUserListState.value = UiState.Loading
                getDummyUserListUseCase().onSuccess { dummyUserList ->
                    _dummyUserListState.value = UiState.Success(dummyUserList)
                }.onFailure { exception: Throwable ->
                    _dummyUserListState.value = UiState.Error(exception.message)
                }
            }
        }
    }
}