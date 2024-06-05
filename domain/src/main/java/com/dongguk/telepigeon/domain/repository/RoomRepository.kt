package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.domain.model.RoomInfoModel

interface RoomRepository {
    suspend fun getRooms(): Result<List<HomeRoomModel>>

    suspend fun postRoom(name: String): Result<Unit>

    suspend fun postEntranceRoom(code: String): Result<String>

    suspend fun deleteRoom(roomId: Int): Result<Unit>

    suspend fun getRoomInfo(roomId: Int): Result<RoomInfoModel>
}
