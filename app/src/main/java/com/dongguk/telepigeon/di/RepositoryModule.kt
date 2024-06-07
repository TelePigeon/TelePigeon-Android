package com.dongguk.telepigeon.di

import com.dongguk.telepigeon.data.repositoryimpl.AuthKakaoRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.AuthRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.CommonRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.HurryRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.ProfileRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.QuestionAnswerRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.RoomRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.TelePigeonRepositoryImpl
import com.dongguk.telepigeon.data.repositoryimpl.WorryRepositoryImpl
import com.dongguk.telepigeon.domain.repository.AuthKakaoRepository
import com.dongguk.telepigeon.domain.repository.AuthRepository
import com.dongguk.telepigeon.domain.repository.CommonRepository
import com.dongguk.telepigeon.domain.repository.HurryRepository
import com.dongguk.telepigeon.domain.repository.ProfileRepository
import com.dongguk.telepigeon.domain.repository.QuestionAnswerRepository
import com.dongguk.telepigeon.domain.repository.RoomRepository
import com.dongguk.telepigeon.domain.repository.TelePigeonRepository
import com.dongguk.telepigeon.domain.repository.WorryRepository
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
    abstract fun bindsAuthKakaoRepository(authKakaoRepositoryImpl: AuthKakaoRepositoryImpl): AuthKakaoRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsCommonRepository(commonRepositoryImpl: CommonRepositoryImpl): CommonRepository

    @Binds
    @Singleton
    abstract fun bindsHurryRepository(hurryRepositoryImpl: HurryRepositoryImpl): HurryRepository

    @Binds
    @Singleton
    abstract fun bindsProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindsQuestionAnswerRepository(questionAnswerRepositoryImpl: QuestionAnswerRepositoryImpl): QuestionAnswerRepository

    @Binds
    @Singleton
    abstract fun bindsRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository

    @Binds
    @Singleton
    abstract fun bindsTelePigeonRepository(telePigeonRepositoryImpl: TelePigeonRepositoryImpl): TelePigeonRepository

    @Binds
    @Singleton
    abstract fun bindsWorryRepository(worryRepositoryImpl: WorryRepositoryImpl): WorryRepository
}
