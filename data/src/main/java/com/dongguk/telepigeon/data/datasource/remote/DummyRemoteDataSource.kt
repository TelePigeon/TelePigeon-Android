package com.dongguk.telepigeon.data.datasource.remote

import com.dongguk.telepigeon.data.model.remote.response.ResponseGetDummyUserListDto

interface DummyRemoteDataSource {
    suspend fun getDummyUserList(page: Int): ResponseGetDummyUserListDto
}