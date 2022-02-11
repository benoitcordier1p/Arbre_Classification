package com.example.arbre_classification.domain.repository

import com.example.arbre_classification.data.models.Record
import com.example.arbre_classification.data.models.TreesComplete

interface TreeRepository {

    suspend fun getTrees(start: String) : TreesComplete

    //suspend fun getTreeByPosition(position:String) : List<Tree>
}