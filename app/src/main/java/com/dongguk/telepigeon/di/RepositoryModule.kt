package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.repositoryimpl.RoomRepositoryImpl
import com.dongguk.telepigeon.domain.repository.RoomRepository
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
    abstract fun bindsRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository
}
