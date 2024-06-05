package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.mapper.toHomeRoomModel
import com.dongguk.telepigeon.data.mapper.toRoomInfoModel
import com.dongguk.telepigeon.data.remote.datasource.RoomRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.request.RequestPostEntranceRoomDto
import com.dongguk.telepigeon.data.remote.model.request.RequestPostRoomDto
import com.dongguk.telepigeon.domain.model.HomeRoomModel
import com.dongguk.telepigeon.domain.model.RoomInfoModel
import com.dongguk.telepigeon.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl
    @Inject
    constructor(
        private val roomRemoteDataSource: RoomRemoteDataSource,
    ) : RoomRepository {
        override suspend fun getRooms(): Result<List<HomeRoomModel>> =
            runCatching {
                roomRemoteDataSource.getRooms().data.rooms.map { responseGetRoomDto ->
                    responseGetRoomDto.toHomeRoomModel()
                }
            }

        override suspend fun postRoom(name: String): Result<Unit> =
            runCatching {
                roomRemoteDataSource.postRoom(requestPostRoomDto = RequestPostRoomDto(name = name))
            }

        override suspend fun postEntranceRoom(code: String): Result<String> =
            runCatching {
                roomRemoteDataSource.postEntranceRoom(requestPostEntranceRoomDto = RequestPostEntranceRoomDto(code = code)).code
            }

        override suspend fun deleteRoom(roomId: Int): Result<Unit> =
            runCatching {
                roomRemoteDataSource.deleteRoom(roomId = roomId)
            }

        override suspend fun getRoomInfo(roomId: Int): Result<RoomInfoModel> =
            runCatching {
                roomRemoteDataSource.getRoomInfo(roomId = roomId).data.toRoomInfoModel()
            }
    }
