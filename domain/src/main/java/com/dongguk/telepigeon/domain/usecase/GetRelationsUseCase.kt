package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.CommonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRelationsUseCase
    @Inject
    constructor(
        private val commonRepository: CommonRepository,
    ) {
        suspend operator fun invoke() = commonRepository.getRelations()
    }
