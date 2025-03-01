package com.example.volindotakehometest.di

import com.example.volindotakehometest.data.handlers.DefaultCoroutineDispatcherProvider
import com.example.volindotakehometest.domain.handlers.CoroutineDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideCoroutineDispatcherProvider(defaultCoroutineDispatcherProvider: DefaultCoroutineDispatcherProvider): CoroutineDispatcherProvider
}