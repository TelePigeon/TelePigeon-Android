package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.repositoryimpl.ProfileRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.RoomRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.TelePigeonRepositoryImpl
import com.dongguk.telepigeon.domain.repository.ProfileRepository
import com.dongguk.telepigeon.domain.repository.RoomRepository
import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsTelePigeonRepository(telePigeonRepositoryImpl: TelePigeonRepositoryImpl): TelePigeonRepository

    @Binds
    @Singleton
    abstract fun bindsProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindsRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository
}
