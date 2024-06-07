package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.mapper.toAuthTokenModel
import com.dongguk.telepigeon.data.remote.datasource.AuthRemoteDataSource
import com.dongguk.telepigeon.domain.model.AuthTokenModel
import com.dongguk.telepigeon.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
): AuthRepository{
    override suspend fun postLogin(): Result<AuthTokenModel> = runCatching {
        authRemoteDataSource.postLogin().data.toAuthTokenModel()
    }

    override suspend fun deleteLogin(): Result<Unit> = runCatching {
        authRemoteDataSource.deleteLogin()
    }

    override suspend fun deleteWithdrawal(): Result<Unit> = runCatching {
        authRemoteDataSource.deleteWithdrawal()
    }

    override suspend fun postReissue(): Result<Unit> = runCatching {
        authRemoteDataSource.postReissue()
    }
}