package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.response.ResponseGetLatestRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetMonthlyReportDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionAnswerDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetQuestionDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestionAnswerService {
    @GET("$ROOMS/{$ROOM_ID}")
    suspend fun getLatestRoomInfo(
        @Path("$ROOM_ID") roomId: Int,
    ): BaseResponseDto<ResponseGetLatestRoomInfoDto>

    @GET("$ROOMS/{$ROOM_ID}/$QUESTIONS")
    suspend fun getQuestion(
        @Path("$ROOM_ID") roomId: Int,
    ): BaseResponseDto<ResponseGetQuestionDto>

    @POST("$ROOMS/{$ROOM_ID}/$QUESTIONS/{$QUESTIONS_ID}/$ANSWERS")
    suspend fun postAnswer(
        @Path("$ROOM_ID") roomId: Int,
        @Path("$QUESTIONS_ID") questionsId: Int,
        @Part image: MultipartBody.Part?,
        @Part("$CONTENT") content: RequestBody
    ): NullableBaseResponseDto<Unit>

    @GET("$ROOMS/{$ROOM_ID}/$TALKS")
    suspend fun getQuestionAnswer(
        @Path("$ROOM_ID") roomId: Int,
        @Query("$DATE") date: String?,
        @Query("$RESPONDENT") respondent: Boolean
    ): BaseResponseDto<ResponseGetQuestionAnswerDto>

    @GET("$ROOMS/{$ROOM_ID}/$REPORTS")
    suspend fun getMonthlyReport(
        @Path("$ROOM_ID") roomId: Int,
        @Query("$DATE") date: String
    ): BaseResponseDto<ResponseGetMonthlyReportDto>

    companion object {
        const val ROOMS = "rooms"
        const val ROOM_ID = "roomId"
        const val QUESTIONS = "questions"
        const val QUESTIONS_ID = "questionsId"
        const val CONTENT = "content"
        const val ANSWERS = "answers"
        const val TALKS = "talks"
        const val REPORTS = "reports"
        const val DATE = "date"
        const val RESPONDENT = "respondent"
    }
}