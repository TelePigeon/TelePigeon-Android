package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.AuthTokenModel

interface AuthRepository {
    suspend fun postLogin(authorization: String, fcmToken: String): Result<AuthTokenModel>

    suspend fun deleteLogout(): Result<Unit>

    suspend fun deleteWithdrawal(): Result<Unit>

    suspend fun postReissue(): Result<Unit>
}
