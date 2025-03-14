package com.aagamshah.slipstreampicks.di

import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.data.repositoryimpl.ConstructorStandingRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.CurrentSeasonRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.DriverStandingRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.GetFantasyHomeRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.HomeRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.LoginRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.NavigationRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.ProfileImageRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.RaceResultRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.SignUpRepositoryImpl
import com.aagamshah.slipstreampicks.domain.repository.ConstructorStandingRepository
import com.aagamshah.slipstreampicks.domain.repository.CurrentSeasonRepository
import com.aagamshah.slipstreampicks.domain.repository.DriverStandingRepository
import com.aagamshah.slipstreampicks.domain.repository.GetFantasyHomeRepository
import com.aagamshah.slipstreampicks.domain.repository.HomeRepository
import com.aagamshah.slipstreampicks.domain.repository.LoginRepository
import com.aagamshah.slipstreampicks.domain.repository.NavigationRepository
import com.aagamshah.slipstreampicks.domain.repository.ProfileImageRepository
import com.aagamshah.slipstreampicks.domain.repository.RaceResultRepository
import com.aagamshah.slipstreampicks.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository {
        return HomeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDriverStandingRepository(apiService: ApiService): DriverStandingRepository {
        return DriverStandingRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideConstructorStandingRepository(apiService: ApiService): ConstructorStandingRepository {
        return ConstructorStandingRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNavigationRepository(apiService: ApiService): NavigationRepository {
        return NavigationRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCurrentSeasonRepository(apiService: ApiService): CurrentSeasonRepository {
        return CurrentSeasonRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRaceResultRepository(apiService: ApiService): RaceResultRepository {
        return RaceResultRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(
        apiService: ApiService,
        userDao: UserDao,
        preferenceManager: PreferenceManager,
    ): SignUpRepository {
        return SignUpRepositoryImpl(apiService, userDao, preferenceManager)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        apiService: ApiService,
        userDao: UserDao,
        preferenceManager: PreferenceManager,
    ): LoginRepository {
        return LoginRepositoryImpl(apiService, userDao, preferenceManager)
    }

    @Provides
    @Singleton
    fun provideGetFantasyHomeRepository(apiService: ApiService): GetFantasyHomeRepository {
        return GetFantasyHomeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUploadProfileImageRepository(
        apiService: ApiService,
        userDao: UserDao
    ): ProfileImageRepository {
        return ProfileImageRepositoryImpl(apiService, userDao)
    }
}