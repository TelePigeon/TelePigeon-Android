package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.request.RequestPutRoomKeywordsExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomExtraDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomKeywordsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileService {
    @GET("$ROOMS/{$ROOM_ID}/$KEYWORDS")
    suspend fun getRoomKeywords(
        @Path("${ROOM_ID}") roomId: Int,
    ): BaseResponseDto<ResponseGetRoomKeywordsDto>

    @GET("$ROOMS/{$ROOM_ID}/$EXTRA")
    suspend fun getRoomExtra(
        @Path("${ROOM_ID}") roomId: Int,
    ): BaseResponseDto<ResponseGetRoomExtraDto>

    @PUT("$ROOMS/{$ROOM_ID}")
    suspend fun putRoomKeywordsExtra(
        @Path("${ROOM_ID}") roomId: Int,
        @Body requestPutRoomKeywordsExtraDto: RequestPutRoomKeywordsExtraDto,
    ): NullableBaseResponseDto<Unit>

    companion object {
        const val ROOMS = "rooms"
        const val ROOM_ID = "roomId"
        const val KEYWORDS = "keywords"
        const val EXTRA = "extra"
    }
}
