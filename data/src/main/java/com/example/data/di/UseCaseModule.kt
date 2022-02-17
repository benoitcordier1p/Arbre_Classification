package com.example.data.di

import com.example.domain.repository.TreeRepository
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
    fun provideUseCase(repo: TreeRepository): GetTreesUseCase {
        return GetTreesUseCase(repo)
    }

}