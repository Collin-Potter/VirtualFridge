package com.example.virtualfridge.di

import android.content.Context
import com.example.virtualfridge.data.AppDatabase
import com.example.virtualfridge.data.ConsumableDao
import com.example.virtualfridge.data.FridgeConsumableDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideConsumableDao(appDatabase: AppDatabase): ConsumableDao {
        return appDatabase.consumableDao()
    }

    @Provides
    fun provideFridgeConsumableDao(appDatabase: AppDatabase): FridgeConsumableDao {
        return appDatabase.fridgeConsumableDao()
    }
}