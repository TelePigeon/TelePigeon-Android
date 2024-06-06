package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.remote.service.HurryService
import com.dongguk.telepigeon.data.remote.service.ProfileService
import com.dongguk.telepigeon.data.remote.service.QuestionAnswerService
import com.dongguk.telepigeon.data.remote.service.RoomService
import com.dongguk.telepigeon.data.remote.service.WorryService
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
    fun providesProfileService(
        @TelePigeon retrofit: Retrofit,
    ): ProfileService =
        retrofit.create(ProfileService::class.java)

    @Provides
    @Singleton
    fun providesHurryService(
        @TelePigeon retrofit: Retrofit,
    ): HurryService =
        retrofit.create(HurryService::class.java)

    @Provides
    @Singleton
    fun providesQuestionAnswerService(
        @TelePigeon retrofit: Retrofit,
    ): QuestionAnswerService =
        retrofit.create(QuestionAnswerService::class.java)

    @Provides
    @Singleton
    fun providesRoomService(
        @TelePigeon retrofit: Retrofit,
    ): RoomService =
        retrofit.create(RoomService::class.java)

    @Provides
    @Singleton
    fun providesWorryService(
        @TelePigeon retrofit: Retrofit,
    ): WorryService =
        retrofit.create(WorryService::class.java)
}
