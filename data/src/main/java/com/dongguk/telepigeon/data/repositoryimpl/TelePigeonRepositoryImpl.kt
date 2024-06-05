package com.dongguk.telepigeon.data.repositoryimpl

import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import javax.inject.Inject

class TelePigeonRepositoryImpl
    @Inject
    constructor(
        private val telePigeonLocalDataSource: TelePigeonLocalDataSource,
    ) : TelePigeonRepository {
        override fun getIsLogin(): Boolean = telePigeonLocalDataSource.isLogin

        override fun setIsLogin(isLogin: Boolean) {
            telePigeonLocalDataSource.isLogin = isLogin
        }

        override fun getAccessToken(): String = telePigeonLocalDataSource.accessToken

        override fun setAccessToken(accessToken: String) {
            telePigeonLocalDataSource.accessToken = accessToken
        }

        override fun getRefreshToken(): String = telePigeonLocalDataSource.refreshToken

        override fun setRefreshToken(refreshToken: String) {
            telePigeonLocalDataSource.refreshToken = refreshToken
        }

        override fun getRoomId(): Int = telePigeonLocalDataSource.roomId

        override fun setRoomId(roomId: Int) {
            telePigeonLocalDataSource.roomId = roomId
        }

        override fun clear() {
            telePigeonLocalDataSource.clear()
        }
    }
