package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree


interface TreeRepositoryRemote {


    suspend fun getTrees() : List<Tree>

    suspend fun getTreesFromCache(position:String) : List<Tree>
}