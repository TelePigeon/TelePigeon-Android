package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.repositoryimpl.DummyRepositoryImpl
import com.dongguk.telepigeon.domain.repository.DummyRepository
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
    abstract fun bindsDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository
}