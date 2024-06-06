package com.dongguk.telepigeon.data.remote.datasourceimpl

import com.dongguk.telepigeon.data.remote.datasource.HurryRemoteDataSource
import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import com.dongguk.telepigeon.data.remote.service.HurryService
import javax.inject.Inject

class HurryRemoteDataSourceImpl
    @Inject
    constructor(
        private val hurryService: HurryService,
    ) : HurryRemoteDataSource {
        override suspend fun postHurry(roomId: Int): NullableBaseResponseDto<Unit> = hurryService.postHurry(roomId = roomId)
    }
