package com.example.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.domain.entities.Trees
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TreeDaoTest {

    private lateinit var database : TreeDatabase
    private lateinit var dao : TreeDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TreeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao =database.treeDao
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insert_Tree_Room() = runBlocking{
        val tree = Trees("1","Japonica",2,123,"Boulevard de stop")
        dao.insertTree(tree)
        val allTrees = dao.getTrees()
        assertThat(allTrees).contains(tree)
    }

    @Test
    fun check_Replace_Strategy() = runBlocking{
        val tree = Trees("1","Japonica",2,123,"Boulevard de stop")
        val treeCopy = Trees("1","Japonica",2,123,"Boulevard de stop")
        dao.insertTree(tree)
        dao.insertTree(treeCopy)
        val allTrees = dao.getTrees()
        assertThat(allTrees).hasSize(1)
    }
}