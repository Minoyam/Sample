package com.example.assignment.server

import com.example.assignment.server.core.AssetFileProvider
import com.example.assignment.server.core.FileProvider
import com.example.assignment.server.core.TestAssetFileProvider
import com.example.assignment.server.di.FileModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FileModule::class]
)
abstract class FakeFileModule {

    @Binds
    @Singleton
    @Named("TestAssetFileProvider")
    abstract fun bindTestFileProvider(
        impl: TestAssetFileProvider,
    ): FileProvider

    @Binds
    @Singleton
    @Named("AssetFileProvider")
    abstract fun bindAssetFileProvider(
        impl: AssetFileProvider,
    ): FileProvider
}