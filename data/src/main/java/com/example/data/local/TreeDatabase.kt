package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.entities.Trees

@Database(
    entities = [Trees::class],
    version = 1
)
abstract class TreeDatabase: RoomDatabase() {

    abstract val treeDao: TreeDao

    companion object {

        const val DATABASE_NAME = "trees_db"
        /*@Volatile
        private var INSTANCE: TreeDatabase? = null

        fun getInstance(context: Context): TreeDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TreeDatabase::class.java,
                    DATABASE_NAME
                ).build().also {
                    INSTANCE = it
                }
            }
        }*/

    }
}