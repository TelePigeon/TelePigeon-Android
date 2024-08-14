package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.MonthlyReportModel
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.domain.model.QuestionModel
import com.dongguk.telepigeon.domain.model.RoomModel

interface QuestionAnswerRepository {
    suspend fun getLatestRoomInfo(roomId: Int): Result<RoomModel>

    suspend fun getQuestion(roomId: Int): Result<QuestionModel>

    suspend fun postAnswer(
        roomId: Int,
        questionId: Int,
        image: String?,
        content: String,
    ): Result<String>

    suspend fun getQuestionAnswer(
        roomId: Int,
        date: String?,
        respondent: Boolean,
    ): Result<List<QuestionAnswerModel>>

    suspend fun getMonthlyReport(
        roomId: Int,
        date: String,
    ): Result<MonthlyReportModel?>
}
