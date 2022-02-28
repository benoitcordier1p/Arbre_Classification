package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entities.Trees

@Dao
interface TreeDao {

    @Query("SELECT * FROM Trees")
    suspend fun getTrees(): List<Trees>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTree(tree: Trees)

    @Query("DELETE FROM Trees")
    fun nukeTable()
}