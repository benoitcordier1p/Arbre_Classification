package com.example.data.di

import com.example.domain.repository.TreeRepositoryLocal
import com.example.domain.repository.TreeRepositoryRemote
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.treesListUseCase.GetTreesUseCase
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
    fun provideUseCase(repoLocal: TreeRepositoryLocal,repoRemote: TreeRepositoryRemote): GetTreesUseCase {
        return GetTreesUseCase(repoLocal,repoRemote)
    }

    @Provides
    @Singleton
    fun provideAddUseCase(repo: TreeRepositoryLocal): AddTreeUseCase {
        return AddTreeUseCase(repo)
    }
}