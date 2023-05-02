package com.imhsp.podcastandroid.di

import com.imhsp.podcastandroid.data.PodcastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providePodcastService(): PodcastService {
        return PodcastService.create()
    }

}