package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.remote.model.response.base.NullableBaseResponseDto
import retrofit2.http.POST
import retrofit2.http.Path

interface HurryService {
    @POST("$ROOMS/{$ROOM_ID}/$HURRIES")
    suspend fun postHurry(
        @Path("${ROOM_ID}") roomId: Int,
    ): NullableBaseResponseDto<Unit>

    companion object {
        const val ROOMS = "rooms"
        const val ROOM_ID = "roomId"
        const val HURRIES = "hurries"
    }
}
