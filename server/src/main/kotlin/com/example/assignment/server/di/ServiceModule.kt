package com.example.assignment.server.di

import com.example.assignment.server.MockInterceptor
import com.example.assignment.server.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideConverterFactory(
    ): Factory {
        return Json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        mockInterceptor: MockInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(mockInterceptor)
            addNetworkInterceptor(interceptor)
            connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideServerRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverter: Factory,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Service.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    companion object {
        internal const val TIME_OUT_SEC = 15L
    }
}
