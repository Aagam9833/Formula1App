package com.aagamshah.slipstreampicks.di

import android.content.Context
import androidx.room.Room
import com.aagamshah.slipstreampicks.data.local.dao.UserDao
import com.aagamshah.slipstreampicks.data.local.database.AppDatabase
import com.aagamshah.slipstreampicks.data.local.sharedpreferences.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }

}