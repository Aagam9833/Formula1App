package com.aagamshah.splitstreampicks.di

import com.aagamshah.splitstreampicks.common.Constants
import com.aagamshah.splitstreampicks.data.remote.ApiService
import com.aagamshah.splitstreampicks.data.repositoryimpl.DriverStandingRepositoryImpl
import com.aagamshah.splitstreampicks.data.repositoryimpl.HomeRepositoryImpl
import com.aagamshah.splitstreampicks.domain.repository.DriverStandingRepository
import com.aagamshah.splitstreampicks.domain.repository.HomeRepository
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


}