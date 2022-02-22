package com.example.data.di

import com.example.domain.repository.TreeRepository
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCaseFromDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(repo: TreeRepository): GetTreesUseCase {
        return GetTreesUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideGetTreesFromDBUseCase(repo: TreeRepository): GetTreesUseCaseFromDB {
        return GetTreesUseCaseFromDB(repo)
    }

    @Provides
    @Singleton
    fun provideAddUseCase(repo: TreeRepository): AddTreeUseCase {
        return AddTreeUseCase(repo)
    }



}