package com.dongguk.telepigeon.feature.calendar.monthlyreport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongguk.telepigeon.domain.model.MonthlyReportModel
import com.dongguk.telepigeon.domain.usecase.GetMonthlyReportUseCase
import com.dongguk.telepigeon.domain.usecase.GetRoomIdUseCase
import com.dongguk.telpigeon.core.ui.util.view.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthlyReportViewModel
@Inject
constructor(
    private val getRoomIdUseCase: GetRoomIdUseCase,
    private val getMonthlyReportUseCase: GetMonthlyReportUseCase
) : ViewModel() {
    private val _getMonthlyReportState = MutableStateFlow<UiState<MonthlyReportModel?>>(UiState.Empty)
    val getMonthlyReportState get() = _getMonthlyReportState.asStateFlow()

    private val roomId = getRoomIdUseCase()

    fun getMonthlyReport(date: String) {
        viewModelScope.launch {
            _getMonthlyReportState.value = UiState.Loading
            getMonthlyReportUseCase(roomId = roomId, date = date).onSuccess { monthlyReportModel ->
                _getMonthlyReportState.value = UiState.Success(monthlyReportModel)
            }.onFailure { exception: Throwable ->
                _getMonthlyReportState.value = UiState.Error(exception.message)
            }
        }
    }
}
