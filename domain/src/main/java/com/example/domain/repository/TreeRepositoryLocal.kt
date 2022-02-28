package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree


interface TreeRepositoryLocal {

    suspend fun getTreesRoom(): List<Tree>

    suspend fun insertTrees(tree : List<Tree>)
}