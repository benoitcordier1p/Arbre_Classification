package com.example.arbre_classification.di

import android.content.Context
import com.example.arbre_classification.util.ConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectionManagerModule {

    @Singleton
    @Provides
    fun provideConnectionManager(@ApplicationContext appContext: Context) :ConnectionManager{
        return ConnectionManager(appContext)
    }
}