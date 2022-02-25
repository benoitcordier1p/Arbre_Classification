package com.example.domain.repository

import com.example.domain.entities.Trees
import com.example.domain.models.Tree


interface TreeRepository {

    suspend fun insertTreesRealm(tree: Trees)
    suspend fun getTreesRealm() : List<Tree>
    suspend fun getTrees(position:String) : List<Tree>

}