package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow


interface TreeRepositoryLocal {

    suspend fun getTreesRoom(): Flow<Resource<List<Tree>>>

    suspend fun insertTrees(tree : List<Tree>)
}