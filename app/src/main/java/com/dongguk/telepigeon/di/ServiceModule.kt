package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.remote.service.RoomService
import com.dongguk.telepigeon.di.qualifier.TelePigeon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesRoomService(
        @TelePigeon retrofit: Retrofit,
    ): RoomService =
        retrofit.create(RoomService::class.java)
}
