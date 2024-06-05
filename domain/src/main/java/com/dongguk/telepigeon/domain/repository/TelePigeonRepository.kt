package com.dongguk.telepigeon.domain.repository

interface TelePigeonRepository {
    fun getIsLogin(): Boolean
    fun setIsLogin(isLogin: Boolean)
    fun getAccessToken(): String
    fun setAccessToken(accessToken: String)
    fun getRefreshToken(): String
    fun setRefreshToken(refreshToken: String)
    fun getRoomId(): Int
    fun setRoomId(roomId: Int)
    fun clear(): Unit
}