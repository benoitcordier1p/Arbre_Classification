package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.TreeDao
import com.example.data.local.TreeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTreeDatabase(app: Application): TreeDatabase {
        return Room.databaseBuilder(
            app,
            TreeDatabase::class.java,
            TreeDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideTreesDao(app: Application): TreeDao{
        return Room.databaseBuilder(
            app,
            TreeDatabase::class.java,
            TreeDatabase.DATABASE_NAME
        ).build().treeDao
    }
}