package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree


interface TreeRepository {

    suspend fun getTreesRoom(start: String): List<Tree>

    suspend fun getTrees(position:String) : List<Tree>

    suspend fun insertTrees(tree : Trees)
}