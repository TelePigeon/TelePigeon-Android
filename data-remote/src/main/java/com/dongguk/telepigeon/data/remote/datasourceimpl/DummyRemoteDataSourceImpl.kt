package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.datasource.remote.DummyRemoteDataSource
import com.dongguk.telepigeon.data.model.remote.response.ResponseGetDummyUserListDto
import com.dongguk.telepigeon.data.remote.service.DummyService
import javax.inject.Inject

class DummyRemoteDataSourceImpl
@Inject
constructor(
    private val dummyService: DummyService
) : DummyRemoteDataSource {
    override suspend fun getDummyUserList(page: Int): ResponseGetDummyUserListDto =
        dummyService.getDummyListUserList(page = page)
}
