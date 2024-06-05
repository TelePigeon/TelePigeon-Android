package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.model.RoomKeywordsExtraModel
import com.dongguk.telepigeon.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PutRoomKeywordExtraUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(roomId: Int, roomKeywordsExtraModel: RoomKeywordsExtraModel) = profileRepository.putRoomKeywordExtra(roomId = roomId, roomKeywordsExtraModel = roomKeywordsExtraModel)
}