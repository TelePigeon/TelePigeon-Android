package com.dongguk.telepigeon.data.local.datasource

interface TelePigeonLocalDataSource {
    var isLogin: Boolean
    var accessToken: String
    var refreshToken: String
    var roomId: Int
    var deviceToken: String

    fun clear(): Unit
}
