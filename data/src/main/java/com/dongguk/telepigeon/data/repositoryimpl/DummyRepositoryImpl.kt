package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.datasource.remote.DummyRemoteDataSource
import com.dongguk.telepigeon.domain.model.UserModel
import com.dongguk.telepigeon.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl
    @Inject
    constructor(
        private val dummyRemoteDataSource: DummyRemoteDataSource,
    ) : DummyRepository {
        override suspend fun getDummyUserList(page: Int): Result<List<UserModel>> =
            runCatching {
                dummyRemoteDataSource.getDummyUserList(page = page).toUserEntityList()
            }
    }
