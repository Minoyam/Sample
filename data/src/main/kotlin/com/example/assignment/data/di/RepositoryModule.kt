package com.example.assignment.data.di

import com.example.assignment.data.repository.RepositoryImpl
import com.example.assignment.domain.repository.Repository
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
    abstract fun providesRepository(impl: RepositoryImpl): Repository
}
