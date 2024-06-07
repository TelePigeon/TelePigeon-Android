package com.dongguk.telepigeon.domain.model

data class AuthTokenModel(
    val accessToken: String,
    val refreshToken: String
)