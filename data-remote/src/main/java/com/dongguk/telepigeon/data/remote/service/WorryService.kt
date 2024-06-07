package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.request.RequestPostWorryDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetWorriesDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorryService {
    @GET("$ROOMS/{$ROOM_ID}/$WORRIES")
    suspend fun getWorries(
        @Path("${ROOM_ID}") roomId: Int,
    ): BaseResponseDto<ResponseGetWorriesDto>

    @POST("$ROOMS/{$ROOM_ID}/$WORRIES")
    suspend fun postWorry(
        @Path("${ROOM_ID}") roomId: Int,
        @Body requestPostWorryDto: RequestPostWorryDto,
    ): NullableBaseResponseDto<Unit>

    @DELETE("$WORRIES/{$WORRY_ID}")
    suspend fun deleteWorry(
        @Path("${WORRY_ID}") worryId: Int,
    ): Response<Unit?>

    companion object {
        const val ROOMS = "rooms"
        const val ROOM_ID = "roomId"
        const val WORRIES = "worries"
        const val WORRY_ID = "worryId"
    }
}
