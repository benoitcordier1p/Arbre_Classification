package com.example.domain.repository

import com.example.domain.models.Tree


interface TreeRepository {

    suspend fun getTrees(start: String): List<Tree>

    //suspend fun getTreeByPosition(position:String) : List<Tree>
}