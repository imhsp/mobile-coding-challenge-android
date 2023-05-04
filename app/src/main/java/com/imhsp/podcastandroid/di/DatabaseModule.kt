package com.imhsp.podcastandroid.di

import android.content.Context
import com.imhsp.podcastandroid.data.AppDatabase
import com.imhsp.podcastandroid.data.FavouriteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideFavouriteDao(appDatabase: AppDatabase): FavouriteDAO {
        return appDatabase.favouriteDAO()
    }
}