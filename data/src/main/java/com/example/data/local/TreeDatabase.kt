package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.model.Trees

@Database(
    entities = [Trees::class],
    version = 2
)
abstract class TreeDatabase: RoomDatabase() {

    abstract val treeDao: TreeDao

    companion object {

        const val DATABASE_NAME = "trees_db"

    }
}