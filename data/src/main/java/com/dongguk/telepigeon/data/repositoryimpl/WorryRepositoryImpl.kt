package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.mapper.toRequestPostWorryDto
import com.dongguk.telepigeon.data.mapper.toRoomWorryModel
import com.dongguk.telepigeon.data.remote.datasource.WorryRemoteDataSource
import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.domain.model.WorryModel
import com.dongguk.telepigeon.domain.repository.WorryRepository
import javax.inject.Inject

class WorryRepositoryImpl @Inject constructor(
    private val worryRemoteDataSource: WorryRemoteDataSource
) : WorryRepository {
    override suspend fun getWorries(roomId: Int): Result<List<RoomWorryModel>> = runCatching {
        worryRemoteDataSource.getWorries(roomId = roomId).data.worries.map { responseGetWorryDto ->
            responseGetWorryDto.toRoomWorryModel()
        }
    }

    override suspend fun postWorry(roomId: Int, worryModel: WorryModel): Result<Unit> = runCatching {
        worryRemoteDataSource.postWorry(roomId = roomId, requestPostWorryDto = worryModel.toRequestPostWorryDto())
    }

    override suspend fun deleteWorry(worryId: Int): Result<Unit> = runCatching {
        worryRemoteDataSource.deleteWorry(worryId = worryId)
    }
}