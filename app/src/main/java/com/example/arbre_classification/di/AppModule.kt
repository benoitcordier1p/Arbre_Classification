package com.example.arbre_classification.di

import com.example.arbre_classification.data.remote.TreeApi
import com.example.arbre_classification.domain.repository.TreeRepositoryImpl
import com.example.arbre_classification.domain.repository.TreeRepository
import com.example.arbre_classification.domain.use_case.treeUseCase.AddTreeUseCase
import com.example.arbre_classification.domain.use_case.treeUseCase.GetTreeUseCase
import com.example.arbre_classification.domain.use_case.treeUseCase.TreeUseCase
import com.example.arbre_classification.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTreeApi() : TreeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TreeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTreeRepository(api:TreeApi) : TreeRepository {
        return TreeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: TreeRepository): TreeUseCase {
        return TreeUseCase(
            getTree = GetTreeUseCase(repository),
            addTree = AddTreeUseCase(repository)
        )
    }
}