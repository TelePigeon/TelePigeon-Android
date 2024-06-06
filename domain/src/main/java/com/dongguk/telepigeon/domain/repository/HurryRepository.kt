package com.dongguk.telepigeon.domain.repository

interface HurryRepository {
    suspend fun postHurry(roomId: Int): Result<String>
}
