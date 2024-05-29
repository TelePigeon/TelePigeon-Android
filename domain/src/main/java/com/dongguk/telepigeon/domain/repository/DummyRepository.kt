package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.UserModel

interface DummyRepository {
    suspend fun getDummyUserList(page: Int = 2): Result<List<UserModel>>
}
