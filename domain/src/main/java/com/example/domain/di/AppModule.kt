package com.example.domain.di

import com.example.data.remote.TreeApi
import com.example.domain.repository.TreeRepository
import com.example.domain.repository.TreeRepositoryImpl
import com.example.domain.util.URL
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
    fun provideTreeApi(): TreeApi {
        return Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TreeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTreeRepository(api: TreeApi): TreeRepository {
        return TreeRepositoryImpl(api)
    }

}