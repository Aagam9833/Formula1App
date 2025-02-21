package com.aagamshah.slipstreampicks.di

import com.aagamshah.slipstreampicks.common.Constants
import com.aagamshah.slipstreampicks.data.remote.ApiService
import com.aagamshah.slipstreampicks.data.repositoryimpl.ConstructorStandingRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.CurrentSeasonRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.DriverStandingRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.HomeRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.NavigationRepositoryImpl
import com.aagamshah.slipstreampicks.data.repositoryimpl.RaceResultRepositoryImpl
import com.aagamshah.slipstreampicks.domain.repository.ConstructorStandingRepository
import com.aagamshah.slipstreampicks.domain.repository.CurrentSeasonRepository
import com.aagamshah.slipstreampicks.domain.repository.DriverStandingRepository
import com.aagamshah.slipstreampicks.domain.repository.HomeRepository
import com.aagamshah.slipstreampicks.domain.repository.NavigationRepository
import com.aagamshah.slipstreampicks.domain.repository.RaceResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

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

}