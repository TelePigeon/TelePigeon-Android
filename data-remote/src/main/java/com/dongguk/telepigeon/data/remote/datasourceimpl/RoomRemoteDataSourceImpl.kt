package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.RoomRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.request.RequestPostEntranceRoomDto
import com.dongguk.telepigeon.data.remote.model.request.RequestPostRoomDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomInfoDto
import com.dongguk.telepigeon.data.remote.model.response.ResponseGetRoomsDto
import com.dongguk.telepigeon.data.remote.model.response.base.BaseResponseDto
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.RoomService
import javax.inject.Inject

class RoomRemoteDataSourceImpl
    @Inject
    constructor(
        private val roomService: RoomService,
    ) : RoomRemoteDataSource {
        override suspend fun getRooms(): BaseResponseDto<ResponseGetRoomsDto> = roomService.getRooms()

        override suspend fun postRoom(requestPostRoomDto: RequestPostRoomDto): NullableBaseResponseDto<Unit> = roomService.postRoom(requestPostRoomDto = requestPostRoomDto)

        override suspend fun postEntranceRoom(requestPostEntranceRoomDto: RequestPostEntranceRoomDto): NullableBaseResponseDto<Unit> = roomService.postEntranceRoom(requestPostEntranceRoomDto = requestPostEntranceRoomDto)

        override suspend fun deleteRoom(roomId: Int): NullableBaseResponseDto<Unit> = roomService.deleteRoom(roomId = roomId)

        override suspend fun getRoomInfo(roomId: Int): BaseResponseDto<ResponseGetRoomInfoDto> = getRoomInfo(roomId = roomId)
    }
