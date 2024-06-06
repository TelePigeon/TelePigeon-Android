package com.dongguk.telepigeon.domain.repository

import com.dongguk.telepigeon.domain.model.RoomWorryModel
import com.dongguk.telepigeon.domain.model.WorryModel

interface WorryRepository {
    suspend fun getWorries(roomId: Int): Result<List<RoomWorryModel>>
    suspend fun postWorry(roomId: Int, worryModel: WorryModel): Result<Unit>
    suspend fun deleteWorry(worryId: Int): Result<Unit>
}