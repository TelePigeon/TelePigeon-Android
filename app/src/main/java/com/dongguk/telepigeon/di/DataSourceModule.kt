package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import com.dongguk.telepigeon.data.local.datasourceimpl.TelePigeonLocalDataSourceImpl
import com.dongguk.telepigeon.data.remote.datasource.ProfileRemoteDataSource
import com.dongguk.telepigeon.data.remote.datasource.RoomRemoteDataSource
import com.dongguk.telepigeon.data.remote.datasourceimpl.ProfileRemoteDataSourceImpl
import com.dongguk.telepigeon.data.remote.datasourceimpl.RoomRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsTelePigeonLocalDataSource(telePigeonLocalDataSourceImpl: TelePigeonLocalDataSourceImpl): TelePigeonLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsProfileRemoteDataSource(profileRemoteDataSourceImpl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsRoomRemoteDataSource(roomRemoteDataSourceImpl: RoomRemoteDataSourceImpl): RoomRemoteDataSource
}
