package com.example.domain.repository

import com.example.domain.models.Tree
import io.realm.RealmConfiguration


interface TreeRepository {

    suspend fun insertTreesRealm(tree: Tree)
    suspend fun getTreesRealm() : List<Tree>
    suspend fun getTrees(position:String) : List<Tree>

}