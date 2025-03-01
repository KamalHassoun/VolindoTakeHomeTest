package com.example.volindotakehometest.di

import com.example.volindotakehometest.data.repositories.FeedRepositoryImpl
import com.example.volindotakehometest.domain.repositories.FeedRepository
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
    abstract fun bindFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): FeedRepository
}