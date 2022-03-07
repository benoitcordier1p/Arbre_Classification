package com.example.data.di

import com.example.data.remote.errorHandler.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ErrorModule {

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandlerImpl {
        return ErrorHandlerImpl()
    }
}