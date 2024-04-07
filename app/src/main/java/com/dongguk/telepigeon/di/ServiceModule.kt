package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.remote.service.DummyService
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
    fun providesDummyService(@TelePigeon retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)
}