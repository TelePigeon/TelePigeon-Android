package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.model.UserEntity
import com.dongguk.telepigeon.domain.repository.DummyRepository

class GetDummyUserListUseCase(private val dummyRepository: DummyRepository) {
    suspend operator fun invoke(page: Int = 2): Result<List<UserEntity>> = dummyRepository.getDummyUserList(page = page)
}