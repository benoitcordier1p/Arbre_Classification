package com.example.arbre_classification.data.repository

import com.example.arbre_classification.data.models.Tree

interface TreeRepository {

    suspend fun getTrees() : List<Tree>

    suspend fun getTreeByPosition(position:String) : List<Tree>
}