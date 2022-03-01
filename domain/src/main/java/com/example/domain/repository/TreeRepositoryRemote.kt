package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow


interface TreeRepositoryRemote {


    suspend fun getTrees() : Flow<Resource<List<Tree>>>

    suspend fun getTreesFromCache(position:String) : Flow<Resource<List<Tree>>>
}