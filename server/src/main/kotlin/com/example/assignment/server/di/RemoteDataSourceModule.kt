package com.example.assignment.server.di

import com.example.assignment.data.source.RemoteDataSource
import com.example.assignment.server.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun providesRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}
