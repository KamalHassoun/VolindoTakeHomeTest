package com.example.volindotakehometest.di

import com.example.volindotakehometest.data.remote.api.FeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideImgurClient(): Retrofit {
        val interceptor = Interceptor { chain: Interceptor.Chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Client-ID f47be0a14763ea7") // Add your header here
                .build()

            return@Interceptor chain.proceed(newRequest)
        }

        val client =  OkHttpClient.Builder()
            .addInterceptor(interceptor = interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideFeedApi(retrofit: Retrofit): FeedApi = retrofit.create(FeedApi::class.java)
}