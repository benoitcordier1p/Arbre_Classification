package com.example.data.di

import com.example.data.local.TreeDao
import com.example.data.remote.TreeApi
import com.example.data.repository.TreeRepositoryLocalImpl
import com.example.data.repository.TreeRepositoryRemoteImpl
import com.example.domain.repository.TreeRepositoryLocal
import com.example.domain.repository.TreeRepositoryRemote
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
    fun provideTreeRepositoryLocal(dao: TreeDao): TreeRepositoryLocal {
        return TreeRepositoryLocalImpl(dao)
    }

    @Provides
    @Singleton
    fun provideTreeRepositoryRemote(api : TreeApi): TreeRepositoryRemote {
        return TreeRepositoryRemoteImpl(api)
    }

}