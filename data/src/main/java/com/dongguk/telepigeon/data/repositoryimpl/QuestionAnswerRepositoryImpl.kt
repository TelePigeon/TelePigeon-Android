package com.dongguk.telepigeon.data.repositoryimpl

import android.content.ContentResolver
import android.net.Uri
import com.dongguk.telepigeon.data.mapper.toCheckQuestionModel
import com.dongguk.telepigeon.data.mapper.toMonthlyReportModel
import com.dongguk.telepigeon.data.mapper.toQuestionAnswerModel
import com.dongguk.telepigeon.data.mapper.toRoomModel
import com.dongguk.telepigeon.data.remote.datasource.QuestionAnswerDataSource
import com.dongguk.telepigeon.data.util.ContentUriRequestBody
import com.dongguk.telepigeon.domain.model.CheckQuestionModel
import com.dongguk.telepigeon.domain.model.MonthlyReportModel
import com.dongguk.telepigeon.domain.model.QuestionAnswerModel
import com.dongguk.telepigeon.domain.model.RoomModel
import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class QuestionAnswerRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    private val questionAnswerDataSource: QuestionAnswerDataSource
) : QuestionAnswerRepository {
    override suspend fun getLatestRoomInfo(roomId: Int): Result<RoomModel> = runCatching {
        questionAnswerDataSource.getLatestRoomInfo(roomId = roomId).data.toRoomModel()
    }

    override suspend fun getQuestion(roomId: Int): Result<CheckQuestionModel> = runCatching {
        questionAnswerDataSource.getQuestion(roomId = roomId).data.toCheckQuestionModel()
    }

    override suspend fun postAnswer(roomId: Int, questionId: Int, image: String?, content: String): Result<Unit> = runCatching {
        questionAnswerDataSource.postAnswer(roomId = roomId, questionId = questionId, image = ContentUriRequestBody(contentResolver = contentResolver, uri = Uri.parse(image)).toFormData(), content = content.toRequestBody("text/plain".toMediaType()))
    }

    override suspend fun getQuestionAnswer(roomId: Int, date: String?, respondent: Boolean): Result<List<QuestionAnswerModel>> = runCatching {
        questionAnswerDataSource.getQuestionAnswer(roomId = roomId, date = date, respondent = respondent).data.sets.map { responseGetQuestionAnswerSetDto ->
            responseGetQuestionAnswerSetDto.toQuestionAnswerModel()
        }
    }

    override suspend fun getMonthlyReport(roomId: Int, date: String): Result<MonthlyReportModel> = runCatching {
        questionAnswerDataSource.getMonthlyReport(roomId = roomId, date = date).data.toMonthlyReportModel()
    }
}