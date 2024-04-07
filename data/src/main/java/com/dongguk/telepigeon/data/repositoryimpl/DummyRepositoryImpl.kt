package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.datasource.remote.DummyRemoteDataSource
import com.dongguk.telepigeon.domain.model.UserEntity
import com.dongguk.telepigeon.domain.repository.DummyRepository
import javax.inject.Inject


class DummyRepositoryImpl @Inject constructor(
    private val dummyRemoteDataSource: DummyRemoteDataSource
) : DummyRepository {
    override suspend fun getDummyUserList(page: Int): Result<List<UserEntity>> = runCatching {
        dummyRemoteDataSource.getDummyUserList(page = page).toUserEntityList()
    }
}