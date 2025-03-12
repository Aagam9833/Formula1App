package com.aagamshah.slipstreampicks.di

import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.repositoryimpl.UserRepositoryImpl
import com.aagamshah.slipstreampicks.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

}