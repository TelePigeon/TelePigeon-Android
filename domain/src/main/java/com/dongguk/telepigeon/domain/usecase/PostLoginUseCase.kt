package com.dongguk.telepigeon.domain.usecase

import com.dongguk.telepigeon.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.postLogin()
}