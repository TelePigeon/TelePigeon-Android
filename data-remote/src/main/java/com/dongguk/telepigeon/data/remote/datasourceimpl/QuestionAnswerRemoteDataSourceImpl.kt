package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.QuestionAnswerRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetLatestRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetMonthlyReportDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionAnswerDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.QuestionAnswerService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class QuestionAnswerRemoteDataSourceImpl @Inject constructor(
    private val questionAnswerService: QuestionAnswerService
) : QuestionAnswerRemoteDataSource {
    override suspend fun getLatestRoomInfo(roomId: Int): BaseResponseDto<ResponseGetLatestRoomInfoDto> = questionAnswerService.getLatestRoomInfo(roomId = roomId)

    override suspend fun getQuestion(roomId: Int): BaseResponseDto<ResponseGetQuestionDto> = questionAnswerService.getQuestion(roomId = roomId)

    override suspend fun postAnswer(roomId: Int, questionId: Int, image: MultipartBody.Part?, content: RequestBody): NullableBaseResponseDto<Unit> = questionAnswerService.postAnswer(roomId = roomId, questionsId = questionId, image = image, content = content)

    override suspend fun getQuestionAnswer(roomId: Int, date: String?, respondent: Boolean): BaseResponseDto<ResponseGetQuestionAnswerDto> = questionAnswerService.getQuestionAnswer(roomId = roomId, date = date, respondent = respondent)

    override suspend fun getMonthlyReport(roomId: Int, date: String): BaseResponseDto<ResponseGetMonthlyReportDto> = questionAnswerService.getMonthlyReport(roomId = roomId, date = date)
}