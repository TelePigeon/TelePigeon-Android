package com.dongguk.telepigeon.feature.calendar.monthlyreport

import androidx.lifecycle.ViewModel
import com.dongguk.telepigeon.domain.model.MonthlyReportModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonthlyReportViewModel
    @Inject
    constructor() : ViewModel() {
        val dummyMonthlyReportModel: MonthlyReportModel? =
            MonthlyReportModel(
                positiveKeywords = listOf("운동", "-", "-"),
                negativeKeywords = listOf("건강", "-", "-"),
            )
    }
