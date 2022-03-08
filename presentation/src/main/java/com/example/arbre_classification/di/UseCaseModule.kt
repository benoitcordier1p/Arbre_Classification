package com.example.arbre_classification.di

import com.example.data.remote.errorHandler.ErrorHandlerImpl
import com.example.data.repository.TreeRepositoryLocal
import com.example.data.repository.TreeRepositoryRemote
import com.example.domain.useCase.addTreeUseCase.AddTreeUseCase
import com.example.domain.useCase.deleteTreeUseCase.DeleteTreeUseCase
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
    fun provideUseCase(repoLocal: TreeRepositoryLocal, repoRemote: TreeRepositoryRemote): GetTreesUseCase {
        return GetTreesUseCase(repoLocal,repoRemote)
    }

    @Provides
    @Singleton
    fun provideAddUseCase(repo: TreeRepositoryLocal): AddTreeUseCase {
        return AddTreeUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeleteUseCase(repo: TreeRepositoryLocal): DeleteTreeUseCase {
        return DeleteTreeUseCase(repo)
    }
}