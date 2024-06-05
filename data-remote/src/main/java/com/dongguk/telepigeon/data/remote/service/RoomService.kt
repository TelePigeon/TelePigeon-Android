package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.request.RequestPostEntranceRoomDto
import com.dongguk.telepigeon.data.remote.model.request.RequestPostRoomDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RoomService {
    @GET(ROOMS)
    suspend fun getRooms(): BaseResponseDto<ResponseGetRoomsDto>

    @POST(ROOMS)
    suspend fun postRoom(
        @Body requestPostRoomDto: RequestPostRoomDto,
    ): NullableBaseResponseDto<Unit>

    @POST("$ROOMS/$ENTRANCE")
    suspend fun postEntranceRoom(
        @Body requestPostEntranceRoomDto: RequestPostEntranceRoomDto,
    ): NullableBaseResponseDto<Unit>

    @DELETE("$ROOMS/{$ROOM_ID}")
    suspend fun deleteRoom(
        @Path("$ROOM_ID") roomId: Int,
    ): NullableBaseResponseDto<Unit>

    @GET("$ROOMS/{$ROOM_ID}/$INFO")
    suspend fun getRoomInfo(
        @Path("$ROOM_ID") roomId: Int,
    ): BaseResponseDto<ResponseGetRoomInfoDto>

    companion object {
        const val ROOMS = "rooms"
        const val ENTRANCE = "entrance"
        const val ROOM_ID = "roomId"
        const val INFO = "info"
    }
}
