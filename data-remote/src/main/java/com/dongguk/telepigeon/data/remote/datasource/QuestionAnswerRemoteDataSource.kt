package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetLatestRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetMonthlyReportDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionAnswerDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface QuestionAnswerRemoteDataSource {
    suspend fun getLatestRoomInfo(roomId: Int): BaseResponseDto<ResponseGetLatestRoomInfoDto>
    suspend fun getQuestion(roomId: Int): BaseResponseDto<ResponseGetQuestionDto>
    suspend fun postAnswer(roomId: Int, questionId: Int, image: MultipartBody.Part?, content: RequestBody): NullableBaseResponseDto<Unit>
    suspend fun getQuestionAnswer(roomId: Int, date: String?, respondent: Boolean): BaseResponseDto<ResponseGetQuestionAnswerDto>
    suspend fun getMonthlyReport(roomId: Int, date: String): BaseResponseDto<ResponseGetMonthlyReportDto>
}