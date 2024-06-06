package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.remote.datasource.CommonRemoteDataSource
import com.dongguk.telepigeon.domain.repository.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject
constructor(
    private val commonRemoteDataSource: CommonRemoteDataSource
) : CommonRepository {
    override suspend fun getKeywords(): Result<List<String>> = runCatching {
        commonRemoteDataSource.getKeywords().data.keywords
    }

    override suspend fun getGenders(): Result<List<String>> = runCatching {
        commonRemoteDataSource.getGenders().data.gender
    }

    override suspend fun getAgeRanges(): Result<List<String>> = runCatching {
        commonRemoteDataSource.getAgeRanges().data.ageRanges
    }

    override suspend fun getRelations(): Result<List<String>> = runCatching {
        commonRemoteDataSource.getRelations().data.relations
    }
}