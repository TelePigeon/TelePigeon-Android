package com.dongguk.telepigeon.di

import android.content.Context
import com.dongguk.telepigeon.data.remote.service.AuthKakaoService
import com.dongguk.telepigeon.data.remote.service.AuthService
import com.dongguk.telepigeon.data.remote.service.CommonService
import com.dongguk.telepigeon.data.remote.service.HurryService
import com.dongguk.telepigeon.data.remote.service.ProfileService
import com.dongguk.telepigeon.data.remote.service.QuestionAnswerService
import com.dongguk.telepigeon.data.remote.service.RoomService
import com.dongguk.telepigeon.data.remote.service.WorryService
import com.dongguk.telepigeon.di.qualifier.TelePigeon
import com.kakao.sdk.user.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthKakaoService(
        @ApplicationContext context: Context,
        userApiClient: UserApiClient,
    ): AuthKakaoService = AuthKakaoService(context = context, userApiClient)

    @Provides
    @Singleton
    fun providesAuthService(
        @TelePigeon retrofit: Retrofit,
    ): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesCommonService(
        @TelePigeon retrofit: Retrofit,
    ): CommonService =
        retrofit.create(CommonService::class.java)

    @Provides
    @Singleton
    fun providesHurryService(
        @TelePigeon retrofit: Retrofit,
    ): HurryService =
        retrofit.create(HurryService::class.java)

    @Provides
    @Singleton
    fun providesProfileService(
        @TelePigeon retrofit: Retrofit,
    ): ProfileService =
        retrofit.create(ProfileService::class.java)

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
