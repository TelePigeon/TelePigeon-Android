package com.dongguk.telepigeon.data.mapper

import com.dongguk.telepigeon.data.remote.model.response.ResponsePostLoginDto
import com.dongguk.telepigeon.domain.model.AuthTokenModel

fun ResponsePostLoginDto.toAuthTokenModel() =
    AuthTokenModel(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )
