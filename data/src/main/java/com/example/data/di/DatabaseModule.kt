package com.example.data.di

import android.app.Application
import android.content.Context
import com.example.data.local.TreeDatabaseOperations
import com.example.domain.models.Tree
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val realmVersion = 1L

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration{
        return RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()
    }

    @Singleton
    @Provides
    fun providesTreeDatabaseOperation(context: Context): TreeDatabaseOperations{
        return TreeDatabaseOperations(context = context)
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext


}