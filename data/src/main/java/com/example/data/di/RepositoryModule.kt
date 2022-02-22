package com.example.data.di

import com.example.data.local.TreeDao
import com.example.data.local.TreeDatabase
import com.example.data.remote.TreeApi
import com.example.data.repository.TreeRepositoryImpl
import com.example.domain.repository.TreeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTreeRepository(dao: TreeDao,api : TreeApi): TreeRepository {
        return TreeRepositoryImpl(dao,api)
    }

}