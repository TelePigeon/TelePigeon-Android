package com.dongguk.telepigeon.data.remote.service

import com.dongguk.telepigeon.data.model.remote.response.ResponseGetDummyUserListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyService {
    @GET("$API/$USERS")
    suspend fun getDummyListUserList(
        @Query("page") page: Int,
    ): ResponseGetDummyUserListDto

    companion object {
        const val API = "api"
        const val USERS = "users"
    }
}
