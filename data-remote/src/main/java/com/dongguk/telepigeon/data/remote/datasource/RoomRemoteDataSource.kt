package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.request.RequestPostEntranceRoomDto
import com.dongguk.telepigeon.data.remote.model.request.RequestPostRoomDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto

interface RoomRemoteDataSource {
    suspend fun getRooms(): BaseResponseDto<ResponseGetRoomsDto>

    suspend fun postRoom(requestPostRoomDto: RequestPostRoomDto): NullableBaseResponseDto<Unit>

    suspend fun postEntranceRoom(requestPostEntranceRoomDto: RequestPostEntranceRoomDto): NullableBaseResponseDto<Unit>

    suspend fun deleteRoom(roomId: Int): NullableBaseResponseDto<Unit>

    suspend fun getRoomInfo(roomId: Int): BaseResponseDto<ResponseGetRoomInfoDto>
}
