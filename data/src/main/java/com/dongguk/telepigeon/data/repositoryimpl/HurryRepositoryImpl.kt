package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.remote.datasource.HurryRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.request.RequestPostEntranceRoomDto
import com.dongguk.telepigeon.domain.repository.HurryRepository
import javax.inject.Inject

class HurryRepositoryImpl @Inject constructor(
    private val hurryRemoteDataSource: HurryRemoteDataSource
) : HurryRepository{
    override suspend fun postHurry(roomId: Int): Result<String> = runCatching {
        hurryRemoteDataSource.postHurry(roomId = roomId).code
    }

}