package com.dongguk.telepigeon.data.remote.datasource

import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto

interface HurryRemoteDataSource {
    suspend fun postHurry(roomId: Int): NullableBaseResponseDto<Unit>
}
