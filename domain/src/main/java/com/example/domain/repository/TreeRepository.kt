package com.example.domain.repository

import com.example.data.models.TreesComplete

interface TreeRepository {

    suspend fun getTrees(start: String): TreesComplete

    //suspend fun getTreeByPosition(position:String) : List<Tree>
}