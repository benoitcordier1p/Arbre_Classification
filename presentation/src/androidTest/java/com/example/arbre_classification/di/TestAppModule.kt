package com.example.arbre_classification.di

import com.example.data.remote.TreeApi
import com.example.data.repository.TreeRepositoryImpl
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
object TestAppModule {

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
    fun provideTreeRepository(api: TreeApi) : com.example.domain.repository.TreeRepository {
        return com.example.data.repository.TreeRepositoryImpl(api)
    }

}