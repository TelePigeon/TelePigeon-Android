package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetMonthlyReportDto
import com.dongguk.telepigeon.domain.model.MonthlyReportModel

fun ResponseGetMonthlyReportDto.toMonthlyReportModel() =
    MonthlyReportModel(
        positiveKeywords = this.positiveKeywords,
        negativeKeywords = this.negativeKeywords,
    )
