package com.example.assignment.server.di

import com.example.assignment.server.core.AssetFileProvider
import com.example.assignment.server.core.FileProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FileModule {
    @Binds
    @Singleton
    abstract fun bindFileProvider(
        impl: AssetFileProvider
    ): FileProvider
}